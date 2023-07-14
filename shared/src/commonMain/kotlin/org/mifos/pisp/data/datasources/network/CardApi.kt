package org.mifos.pisp.data.datasources.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.get
import io.ktor.client.utils.buildHeaders
import org.mifos.pisp.base.Response
import org.mifos.pisp.usecase.fetchCards.FetchCardsRequest
import org.mifos.pisp.usecase.fetchCards.FetchCardsResponse

class CardApi(private val httpClient: HttpClient) {

    suspend fun fetchCards(request: FetchCardsRequest): Response<FetchCardsResponse> {
        try {
            val response = httpClient.get(API_HOST + CARDS_PATH) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
            }.body<FetchCardsResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }
}
