package com.gblrod.radianthub.di

import androidx.room.Room
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepositoryImpl
import com.gblrod.radianthub.data.room.database.RadiantHubDatabase
import com.gblrod.radianthub.data.room.migrations.MIGRATION_1_2
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {

    // Room
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = RadiantHubDatabase::class.java,
            name = "RadiantHub.db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    single {
        get<RadiantHubDatabase>().favoriteDao()
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(
            dao = get()
        )
    }
}