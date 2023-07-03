package org.mifos.openbanking.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.mifos.openbanking.ApplicationDispatcher
import org.mifos.openbanking.Greeting
import org.mifos.openbanking.data.datasources.disk.DiskDataSource
import org.mifos.openbanking.data.datasources.disk.createDatabase
import org.mifos.openbanking.data.datasources.disk.preferencesHelper.Preferences
import org.mifos.openbanking.data.datasources.disk.preferencesHelper.PreferencesHelper
import kotlin.coroutines.CoroutineContext

val commonModule = module {
    single<CoroutineContext> { ApplicationDispatcher }
    singleOf(::Greeting)
    singleOf(::DiskDataSource)
    singleOf(::PreferencesHelper)
    singleOf<Preferences> {
        Preferences("prefs_storage")
    }
    factory {
        createDatabase(get())
    }
}
