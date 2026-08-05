package com.gblrod.radianthub.ui.features.tiers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.R
import com.gblrod.radianthub.core.connectivity.RetryManager
import com.gblrod.radianthub.core.events.AppEvents
import com.gblrod.radianthub.domain.tiers.repository.TiersRepository
import com.gblrod.radianthub.ui.features.tiers.model.TierGroup
import com.gblrod.radianthub.ui.features.tiers.state.TiersUiState
import com.gblrod.radianthub.ui.shared.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TiersViewModel(
    private val repository: TiersRepository,
    private val retryManager: RetryManager
) : ViewModel() {
    private val _tiersState = MutableStateFlow<TiersUiState>(TiersUiState.Loading)
    val tiersState: StateFlow<TiersUiState> = _tiersState

    private val _initialTierUuid = MutableStateFlow<Int?>(null)
    val initialTierUuid: StateFlow<Int?> = _initialTierUuid

    init {
        observeRetry()
        fetchTiers()

        viewModelScope.launch {
            AppEvents.languageChanged.collect {
                fetchTiers()
            }
        }
    }

    private fun observeRetry() {
        viewModelScope.launch {
            retryManager.retryAll.collect {
                fetchTiers()
            }
        }
    }

    fun fetchTiers() {
        viewModelScope.launch {
            _tiersState.value = TiersUiState.Loading

            safeApiCall(
                onHttpError = { code ->
                    _tiersState.value =
                        TiersUiState.Error(
                            messageResId = R.string.ui_state_http_exception,
                            code = code
                        )
                },

                onIoError = {
                    _tiersState.value =
                        TiersUiState.Error(messageResId = R.string.ui_state_io_exception)
                },

                onGenericError = {
                    _tiersState.value =
                        TiersUiState.Error(messageResId = R.string.ui_state_generic_error)
                }
            ) {
                val tiers = repository.getTiers()

                val groups = tiers.filter { tier ->
                    tier.tier >= 3
                }
                    .groupBy { tier ->
                        tier.divisionName
                    }
                    .map { (rankName, tiers) ->
                        TierGroup(
                            rankName = rankName,
                            tiers = tiers.sortedBy { it.tier }
                        )
                    }
                    .sortedBy {
                        it.tiers.first().tier
                    }

                _tiersState.value = TiersUiState.Success(tiers = groups)
            }
        }
    }

    fun selectTier(tier: Int?) {
        _initialTierUuid.value = tier
    }

    fun clearSelectedTier() {
        _initialTierUuid.value = null
    }

    fun retry() {
        retryManager.retry()
    }
}