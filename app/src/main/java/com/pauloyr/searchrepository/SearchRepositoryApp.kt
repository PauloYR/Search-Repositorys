package com.pauloyr.searchrepository

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SearchRepositoryApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SearchRepositoryApp)
            androidLogger(Level.NONE)
            modules(
                serviceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}