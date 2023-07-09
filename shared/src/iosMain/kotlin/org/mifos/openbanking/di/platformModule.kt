package org.mifos.openbanking.di

import org.koin.dsl.module
import org.mifos.openbanking.DriverFactoryImpl
import org.mifos.openbanking.Platform
import org.mifos.openbanking.PlatformImpl
import org.mifos.openbanking.disk.DriverFactory

actual fun platformModule() = module {
    single<DriverFactory> { DriverFactoryImpl() }
    single<Platform> { PlatformImpl() }
}
