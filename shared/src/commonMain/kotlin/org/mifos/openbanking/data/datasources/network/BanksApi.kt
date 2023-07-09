package org.mifos.openbanking.data.datasources.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.domain.usecase.fetchBanks.FetchBanksRequest
import org.mifos.openbanking.domain.usecase.fetchBanks.FetchBanksResponse

class BanksApi(private val httpClient: HttpClient) {

    suspend fun fetchBanks(request: FetchBanksRequest): Response<FetchBanksResponse> {
        return try {
            val response = httpClient.get(API_HOST + BANKS_PATH).body<FetchBanksResponse>()
            Response.Success(response)
        } catch (exp: ClientRequestException) {
            Response.Error(exp)
        } catch (exp: Exception) {
            Response.Error(exp)
        }
    }
}
