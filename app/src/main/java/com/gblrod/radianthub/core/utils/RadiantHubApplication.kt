package com.gblrod.radianthub.core.utils

import android.app.Application
import com.gblrod.radianthub.di.appModule
import com.gblrod.radianthub.di.preferencesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class RadiantHubApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@RadiantHubApplication)
            modules(
                appModule,
                preferencesModule
            )
        }
    }
}