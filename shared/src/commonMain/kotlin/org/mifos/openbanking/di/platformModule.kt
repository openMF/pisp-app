package org.mifos.openbanking.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.mifos.openbanking.Platform

val platformModule = module {
    singleOf(::Platform)
}
