package org.mifos.pisp.di

import org.koin.dsl.module
import org.mifos.pisp.DriverFactoryImpl
import org.mifos.pisp.Platform
import org.mifos.pisp.PlatformImpl
import org.mifos.pisp.disk.DriverFactory

actual fun platformModule() = module {
    single<DriverFactory> { DriverFactoryImpl() }
    single<Platform> { PlatformImpl() }
}
