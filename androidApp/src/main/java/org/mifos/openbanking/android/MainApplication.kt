package org.mifos.openbanking.android

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.mifos.openbanking.android.di.androidModule
import org.mifos.openbanking.di.appModules

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MainApplication", "onCreate()")

        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(appModules() + androidModule)
        }

        // App.appLaunch()
    }
}
