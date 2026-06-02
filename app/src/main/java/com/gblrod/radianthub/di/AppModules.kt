package com.gblrod.radianthub.di

import com.gblrod.radianthub.data.agents.remote.api.AgentsApi
import com.gblrod.radianthub.data.agents.repository.AgentsRepositoryImpl
import com.gblrod.radianthub.data.cards.remote.api.CardsApi
import com.gblrod.radianthub.data.cards.repository.CardsRepositoryImpl
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepositoryImpl
import com.gblrod.radianthub.data.maps.remote.api.MapsApi
import com.gblrod.radianthub.data.maps.repository.MapsRepositoryImpl
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.domain.cards.repository.CardsRepository
import com.gblrod.radianthub.domain.maps.repository.MapsRepository
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.cards.viewmodel.CardsViewModel
import com.gblrod.radianthub.ui.features.favorites.viewmodel.FavoritesViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.features.search.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Network
    single {
        Retrofit.Builder()
            .baseUrl("https://valorant-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<AgentsRepository> {
        AgentsRepositoryImpl(
            api = get()
        )
    }

    single<MapsRepository> {
        MapsRepositoryImpl(
            api = get()
        )
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(
            dao = get()
        )
    }

    single<CardsRepository> {
        CardsRepositoryImpl(
            api = get()
        )
    }

    // API (Agents)
    single<AgentsApi> {
        get<Retrofit>().create(AgentsApi::class.java)
    }

    // API (Maps)
    single<MapsApi> {
        get<Retrofit>().create(MapsApi::class.java)
    }

    // API (Cards)
    single<CardsApi> {
        get<Retrofit>().create(CardsApi::class.java)
    }

    // ViewModels
    viewModel {
        AgentsViewModel(
            repository = get(),
            favoriteRepository = get()
        )
    }

    viewModel {
        MapsViewModel(
            repository = get()
        )
    }

    viewModel {
        FavoritesViewModel(
            repository = get()
        )
    }

    viewModel {
        CardsViewModel(
            repository = get(),
            favoriteRepository = get()
        )
    }

    viewModel {
        SearchViewModel(
            agentsRepository = get(),
            mapsRepository = get(),
            cardsRepository = get()
        )
    }
}