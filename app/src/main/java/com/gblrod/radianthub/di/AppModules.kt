package com.gblrod.radianthub.di

import com.gblrod.radianthub.data.agents.remote.api.AgentsApi
import com.gblrod.radianthub.data.agents.repository.AgentsRepositoryImpl
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
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

    // API (Agents)
    single<AgentsApi> {
        get<Retrofit>().create(AgentsApi::class.java)
    }

    // ViewModel
    viewModel {
        AgentsViewModel(
            repository = get()
        )
    }
}