package com.gblrod.radianthub.ui.features.cards.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.R
import com.gblrod.radianthub.core.connectivity.RetryManager
import com.gblrod.radianthub.core.events.AppEvents
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
import com.gblrod.radianthub.data.room.model.FavoriteType
import com.gblrod.radianthub.domain.cards.model.Card
import com.gblrod.radianthub.domain.cards.repository.CardsRepository
import com.gblrod.radianthub.ui.features.cards.state.CardsUiState
import com.gblrod.radianthub.ui.shared.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CardsViewModel(
    private val repository: CardsRepository,
    private val favoriteRepository: FavoriteRepository,
    private val retryManager: RetryManager
) : ViewModel() {

    private val _cardsState = MutableStateFlow<CardsUiState>(CardsUiState.Loading)
    val cardsState: StateFlow<CardsUiState> = _cardsState

    private val _initialCardUuid = MutableStateFlow<String?>(null)
    val initialCardUuid: StateFlow<String?> = _initialCardUuid

    val favorites =
        favoriteRepository.observeFavorites().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    init {
        observeRetry()
        fetchCards()

        viewModelScope.launch {
            AppEvents.languageChanged.collect {
                fetchCards()
            }
        }
    }

    private fun observeRetry() {
        viewModelScope.launch {
            retryManager.retryAll.collect {
                if (_cardsState.value is CardsUiState.Error) {
                    fetchCards()
                }
            }
        }
    }

    fun fetchCards() {
        viewModelScope.launch {
            _cardsState.value = CardsUiState.Loading

            safeApiCall(
                onHttpError = { code ->
                    _cardsState.value =
                        CardsUiState.Error(
                            messageResId = R.string.ui_state_http_exception,
                            code = code
                        )
                },

                onIoError = {
                    _cardsState.value =
                        CardsUiState.Error(messageResId = R.string.ui_state_io_exception)
                },

                onGenericError = {
                    _cardsState.value =
                        CardsUiState.Error(messageResId = R.string.ui_state_generic_error)
                }
            ) {
                val cards = repository.getCards()
                _cardsState.value = CardsUiState.Success(cards = cards)
            }
        }
    }

    fun isFavorite(uuid: String): Flow<Boolean> {
        return favoriteRepository.isFavorite(uuid)
    }

    fun toggleFavorite(card: Card) {
        viewModelScope.launch {
            val isFavorite = favoriteRepository.isFavorite(card.uuid).first()

            if (isFavorite) {
                favoriteRepository.removeFavorite(
                    uuid = card.uuid
                )

            } else {
                favoriteRepository.toggleFavorite(
                    uuid = card.uuid,
                    name = card.name,
                    imageUrl = card.icon,
                    type = FavoriteType.CARD,
                    index = favorites.value.size
                )
            }
        }
    }

    fun selectCard(uuid: String) {
        _initialCardUuid.value = uuid
    }

    fun clearSelectedCard() {
        _initialCardUuid.value = null
    }

    fun retry() {
        retryManager.retry()
    }
}