package org.mifos.pisp.data.repository

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.datasources.network.NetworkDataSource
import org.mifos.pisp.usecase.createClient.CreateClientRequest
import org.mifos.pisp.usecase.createClient.CreateClientResponse
import org.mifos.pisp.usecase.createTransactionRequest.CreateTransactionRequestRequest
import org.mifos.pisp.usecase.createTransactionRequest.CreateTransactionRequestResponse
import org.mifos.pisp.usecase.fetchAccounts.FetchAccountsRequest
import org.mifos.pisp.usecase.fetchAccounts.FetchAccountsResponse
import org.mifos.pisp.usecase.fetchBalances.FetchBalancesRequest
import org.mifos.pisp.usecase.fetchBalances.FetchBalancesResponse
import org.mifos.pisp.usecase.fetchBanks.FetchBanksRequest
import org.mifos.pisp.usecase.fetchBanks.FetchBanksResponse
import org.mifos.pisp.usecase.fetchCards.FetchCardsRequest
import org.mifos.pisp.usecase.fetchCards.FetchCardsResponse
import org.mifos.pisp.usecase.fetchTransactionById.FetchTransactionByIdRequest
import org.mifos.pisp.usecase.fetchTransactionById.FetchTransactionByIdResponse
import org.mifos.pisp.usecase.fetchTransactionRequests.FetchTransactionRequestsRequest
import org.mifos.pisp.usecase.fetchTransactionRequests.FetchTransactionRequestsResponse
import org.mifos.pisp.usecase.fetchTransactions.FetchTransactionsRequest
import org.mifos.pisp.usecase.fetchTransactions.FetchTransactionsResponse
import org.mifos.pisp.usecase.loginClient.LoginClientRequest
import org.mifos.pisp.usecase.loginClient.LoginClientResponse

class OpenBankingRepository(
    private val networkDataSource: NetworkDataSource
) {

    suspend fun createClient(request: CreateClientRequest): Response<CreateClientResponse> {
        return networkDataSource.getClientApi()
            .createClient(request.name, request.contact, request.scopes, request.redirectUris);
    }

    suspend fun loginClient(request: LoginClientRequest): Response<LoginClientResponse> {
        return networkDataSource.getClientApi().loginClient(request);
    }

    suspend fun fetchAccounts(request: FetchAccountsRequest): Response<FetchAccountsResponse> {
        return networkDataSource.getClientApi().fetchAccounts(request)
    }

    suspend fun fetchBalances(request: FetchBalancesRequest): Response<FetchBalancesResponse> {
        return networkDataSource.getClientApi().fetchBalances(request)
    }

    suspend fun fetchBanks(request: FetchBanksRequest): Response<FetchBanksResponse> {
        return networkDataSource.getBankApi().fetchBanks(request)
    }

    suspend fun createTransactionRequest(request: CreateTransactionRequestRequest): Response<CreateTransactionRequestResponse> {
        return networkDataSource.getTransactionApi().createTransactionRequest(request)
    }

    suspend fun fetchTransactionRequests(request: FetchTransactionRequestsRequest): Response<FetchTransactionRequestsResponse> {
        return networkDataSource.getTransactionApi().fetchTransactionRequests(request)
    }

    suspend fun fetchTransactionById(request: FetchTransactionByIdRequest): Response<FetchTransactionByIdResponse> {
        return networkDataSource.getTransactionApi().fetchTransactionById(request)
    }

    suspend fun fetchTransactions(request: FetchTransactionsRequest): Response<FetchTransactionsResponse> {
        return networkDataSource.getTransactionApi().fetchTransactions(request)
    }

    suspend fun fetchCards(request: FetchCardsRequest): Response<FetchCardsResponse> {
        return networkDataSource.getCardApi().fetchCards(request)
    }

}
