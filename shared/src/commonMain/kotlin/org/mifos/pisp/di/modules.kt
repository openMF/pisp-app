package org.mifos.pisp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.mifos.pisp.ApplicationDispatcher
import org.mifos.pisp.Greeting
import org.mifos.pisp.data.datasources.disk.DiskDataSource
import org.mifos.pisp.data.datasources.disk.createDatabase
import org.mifos.pisp.data.datasources.disk.dao.AccountDao
import org.mifos.pisp.data.datasources.disk.preferencesHelper.Preferences
import org.mifos.pisp.data.datasources.disk.preferencesHelper.PreferencesHelper
import org.mifos.pisp.data.datasources.network.BanksApi
import org.mifos.pisp.data.datasources.network.CardApi
import org.mifos.pisp.data.datasources.network.ClientApi
import org.mifos.pisp.data.datasources.network.NetworkDataSource
import org.mifos.pisp.data.datasources.network.TransactionApi
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.createClient.CreateClientUseCase
import org.mifos.pisp.usecase.createTransactionRequest.CreateTransactionRequestUseCase
import org.mifos.pisp.usecase.fetchAccounts.FetchAccountsUseCase
import org.mifos.pisp.usecase.fetchBalances.FetchBalancesUseCase
import org.mifos.pisp.usecase.fetchBanks.FetchBanksUseCase
import org.mifos.pisp.usecase.fetchCards.FetchCardsUseCase
import org.mifos.pisp.usecase.fetchTransactionById.FetchTransactionByIdUseCase
import org.mifos.pisp.usecase.fetchTransactionRequests.FetchTransactionRequestsUseCase
import org.mifos.pisp.usecase.fetchTransactions.FetchTransactionsUseCase
import org.mifos.pisp.usecase.loginClient.LoginClientUseCase
import kotlin.coroutines.CoroutineContext

fun commonModules() = listOf<Module>(
    commonModule(),
    networkModule(),
    dataModule(),
    domainModule()
)

expect fun platformModule(): Module

internal fun commonModule() = module {
    single<CoroutineContext> { ApplicationDispatcher }
    singleOf(::Greeting)
}

internal fun networkModule() = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    singleOf(::ClientApi)
    singleOf(::BanksApi)
    singleOf(::TransactionApi)
    singleOf(::CardApi)
    singleOf(::NetworkDataSource)
}

internal fun dataModule() = module {
    singleOf(::OpenBankingRepository)
    singleOf(::DiskDataSource)
    singleOf(::PreferencesHelper)
    singleOf<Preferences> {
        Preferences("prefs_storage")
    }
    factory {
        createDatabase(get())
    }
    singleOf(::AccountDao)
}

internal fun domainModule() = module {
    singleOf(::CreateClientUseCase)
    singleOf(::LoginClientUseCase)
    singleOf(::FetchAccountsUseCase)
    singleOf(::FetchBalancesUseCase)
    singleOf(::FetchBanksUseCase)
    singleOf(::CreateTransactionRequestUseCase)
    singleOf(::FetchTransactionRequestsUseCase)
    singleOf(::FetchCardsUseCase)
    singleOf(::FetchTransactionByIdUseCase)
    singleOf(::FetchTransactionsUseCase)
}
