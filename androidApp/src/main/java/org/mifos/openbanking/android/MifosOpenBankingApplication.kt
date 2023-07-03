package org.mifos.openbanking.android

import android.app.Application
import org.mifos.openbanking.viewModel.app.App

class MifosOpenBankingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        println("Application init")
        App.appLaunch()
    }
}
