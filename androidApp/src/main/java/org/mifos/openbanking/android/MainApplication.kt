package org.mifos.openbanking.android

import android.app.Application
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.mifos.openbanking.di.commonModules
import org.mifos.openbanking.di.platformModule

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MainApplication", "onCreate()")

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(commonModules() + platformModule())
        }

        // App.appLaunch()
    }
}
