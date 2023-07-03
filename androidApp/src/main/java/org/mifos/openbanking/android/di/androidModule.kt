package org.mifos.openbanking.android.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.mifos.openbanking.disk.DriverFactory

val androidModule = module {
    singleOf(::DriverFactory)
}
