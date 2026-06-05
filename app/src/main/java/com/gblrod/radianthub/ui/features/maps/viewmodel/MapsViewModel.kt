package com.gblrod.radianthub.ui.features.maps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.R
import com.gblrod.radianthub.core.events.AppEvents
import com.gblrod.radianthub.domain.maps.repository.MapsRepository
import com.gblrod.radianthub.ui.features.maps.state.MapsUiState
import com.gblrod.radianthub.ui.shared.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapsViewModel(
    private val repository: MapsRepository
) : ViewModel() {
    private val _mapsState = MutableStateFlow<MapsUiState>(MapsUiState.Loading)
    val mapsState: StateFlow<MapsUiState> = _mapsState

    private val _initialMapUuid = MutableStateFlow<String?>(null)
    val initialMapUuid: StateFlow<String?> = _initialMapUuid

    init {
        if (_mapsState.value !is MapsUiState.Success) {
            fetchMaps()
        }

        viewModelScope.launch {
            AppEvents.languageChanged.collect {
                fetchMaps()
            }
        }
    }

    fun fetchMaps() {
        viewModelScope.launch {
            _mapsState.value = MapsUiState.Loading

            safeApiCall(
                onHttpError = { code ->
                    _mapsState.value =
                        MapsUiState.Error(
                            messageResId = R.string.ui_state_http_exception,
                            code = code
                        )
                },

                onIoError = {
                    _mapsState.value =
                        MapsUiState.Error(messageResId = R.string.ui_state_io_exception)
                },

                onGenericError = {
                    _mapsState.value =
                        MapsUiState.Error(messageResId = R.string.ui_state_generic_error)
                }
            ) {
                val maps = repository.getMaps()
                _mapsState.value = MapsUiState.Success(maps = maps)
            }
        }
    }

    fun selectMap(uuid: String) {
        _initialMapUuid.value = uuid
    }

    fun clearSelectedMap() {
        _initialMapUuid.value = null
    }

    fun retry() {
        fetchMaps()
    }
}