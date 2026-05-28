package com.gblrod.radianthub.di

import com.gblrod.radianthub.data.agents.remote.api.AgentsApi
import com.gblrod.radianthub.data.agents.repository.AgentsRepositoryImpl
import com.gblrod.radianthub.data.maps.remote.api.MapsApi
import com.gblrod.radianthub.data.maps.repository.MapsRepositoryImpl
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.domain.maps.repository.MapsRepository
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel
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

    // API (Agents)
    single<AgentsApi> {
        get<Retrofit>().create(AgentsApi::class.java)
    }

    // API (Maps)
    single<MapsApi> {
        get<Retrofit>().create(MapsApi::class.java)
    }

    // ViewModels
    viewModel {
        AgentsViewModel(
            repository = get()
        )
    }

    viewModel {
        MapsViewModel(
            repository = get()
        )
    }
}