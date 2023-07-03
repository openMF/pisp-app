package org.mifos.openbanking.data.datasources.network

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.utils.buildHeaders
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.domain.usecase.createClient.CreateClientResponse
import org.mifos.openbanking.domain.usecase.fetchAccounts.FetchAccountsRequest
import org.mifos.openbanking.domain.usecase.fetchAccounts.FetchAccountsResponse
import org.mifos.openbanking.domain.usecase.fetchBalances.FetchBalancesRequest
import org.mifos.openbanking.domain.usecase.fetchBalances.FetchBalancesResponse
import org.mifos.openbanking.domain.usecase.loginClient.LoginClientRequest
import org.mifos.openbanking.domain.usecase.loginClient.LoginClientResponse

class ClientApi {

    suspend fun createClient(
        name: String,
        contact: String,
        scopes: ArrayList<String>,
        redirectUris: ArrayList<String>
    ): Response<CreateClientResponse> {
        return Response.Error(IllegalStateException())
    }

    suspend fun loginClient(request: LoginClientRequest): Response<LoginClientResponse> {
        try {
            val response = httpClient.post(API_HOST + DIRECT_AUTHENTICATION_PATH) {
                buildHeaders {
                    append(
                        "Authorization",
                        "DirectLogin username=${request.username},password=${request.password},consumer_key=${request.consumer_key}"
                    )
                }
            }.body<LoginClientResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }

    suspend fun fetchAccounts(request: FetchAccountsRequest): Response<FetchAccountsResponse> {
        try {
            val response = httpClient.get(API_HOST + ACCOUNTS_PATH) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
            }.body<FetchAccountsResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }

    suspend fun fetchBalances(request: FetchBalancesRequest): Response<FetchBalancesResponse> {
        try {
            val response = httpClient.get(API_HOST + bankBalancePath(request.bankId)) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
            }.body<FetchBalancesResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }
}
