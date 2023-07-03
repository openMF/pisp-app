package org.mifos.openbanking.di

import org.koin.core.module.Module

fun appModules(): List<Module> = listOf(
    platformModule,
    commonModule
)
