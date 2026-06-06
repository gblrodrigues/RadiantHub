package com.gblrod.radianthub.di

import com.gblrod.radianthub.core.localization.ApiLanguageProvider
import com.gblrod.radianthub.data.agents.remote.api.AgentsApi
import com.gblrod.radianthub.data.agents.repository.AgentsRepositoryImpl
import com.gblrod.radianthub.data.cards.remote.api.CardsApi
import com.gblrod.radianthub.data.cards.repository.CardsRepositoryImpl
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepositoryImpl
import com.gblrod.radianthub.data.maps.remote.api.MapsApi
import com.gblrod.radianthub.data.maps.repository.MapsRepositoryImpl
import com.gblrod.radianthub.data.tiers.remote.api.TiersApi
import com.gblrod.radianthub.data.tiers.repository.TiersRepositoryImpl
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.domain.cards.repository.CardsRepository
import com.gblrod.radianthub.domain.maps.repository.MapsRepository
import com.gblrod.radianthub.domain.tiers.repository.TiersRepository
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.cards.viewmodel.CardsViewModel
import com.gblrod.radianthub.ui.features.favorites.viewmodel.FavoritesViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.features.search.viewmodel.SearchViewModel
import com.gblrod.radianthub.ui.features.tiers.viewmodel.TiersViewModel
import org.koin.android.ext.koin.androidContext
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
            api = get(),
            languageProvider = get()
        )
    }

    single<MapsRepository> {
        MapsRepositoryImpl(
            api = get(),
            languageProvider = get()
        )
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(
            dao = get()
        )
    }

    single<CardsRepository> {
        CardsRepositoryImpl(
            api = get(),
            languageProvider = get()
        )
    }

    single<TiersRepository> {
        TiersRepositoryImpl(
            api = get(),
            languageProvider = get()
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

    // API (Tiers)
    single<TiersApi> {
        get<Retrofit>().create(TiersApi::class.java)
    }

    // Language
    single {
        ApiLanguageProvider(
            context = androidContext()
        )
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
            cardsRepository = get(),
            tiersRepository = get()
        )
    }

    viewModel {
        TiersViewModel(
            repository = get()
        )
    }
}