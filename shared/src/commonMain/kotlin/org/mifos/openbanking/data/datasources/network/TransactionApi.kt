package org.mifos.openbanking.data.datasources.network

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.*
import io.ktor.client.utils.buildHeaders
import io.ktor.http.content.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.domain.usecase.createTransactionRequest.CreateTransactionRequestRequest
import org.mifos.openbanking.domain.usecase.createTransactionRequest.CreateTransactionRequestResponse
import org.mifos.openbanking.domain.usecase.fetchTransactionById.FetchTransactionByIdRequest
import org.mifos.openbanking.domain.usecase.fetchTransactionById.FetchTransactionByIdResponse
import org.mifos.openbanking.domain.usecase.fetchTransactionRequests.FetchTransactionRequestsRequest
import org.mifos.openbanking.domain.usecase.fetchTransactionRequests.FetchTransactionRequestsResponse
import org.mifos.openbanking.domain.usecase.fetchTransactions.FetchTransactionsRequest
import org.mifos.openbanking.domain.usecase.fetchTransactions.FetchTransactionsResponse

@Serializable
data class CreateTransactionBody(
    @SerialName("to") val to: To,
    @SerialName("value") val value: Value,
    @SerialName("description") val description: String,
) {
    @Serializable
    class To(
        @SerialName("bank_id") val bankId: String,
        @SerialName("account_id") val accountId: String,
    )

    @Serializable
    class Value(
        @SerialName("currency") val currency: String,
        @SerialName("amount") val amount: String,
    )
}

class TransactionApi(private val httpClient: HttpClient) {

    suspend fun createTransactionRequest(request: CreateTransactionRequestRequest): Response<CreateTransactionRequestResponse> {
        try {
            val response = httpClient.post(
                urlString = API_HOST + createTransactionRequestPath(
                    request.sourceBankId,
                    request.sourceAccountId
                )
            ) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
                setBody(
                    CreateTransactionBody(
                        to = CreateTransactionBody.To(
                            bankId = request.destinationBankId,
                            accountId = request.destinationAccountId
                        ),
                        value = CreateTransactionBody.Value(
                            currency = request.currency,
                            amount = request.amount.toString()
                        ),
                        description = request.description
                    )
                )
            }.body<CreateTransactionRequestResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }

    suspend fun fetchTransactionRequests(request: FetchTransactionRequestsRequest): Response<FetchTransactionRequestsResponse> {
        try {
            val response = httpClient.get(
                API_HOST + fetchTransactionRequestsPath(
                    request.bankId,
                    request.accountId
                )
            ) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
            }.body<FetchTransactionRequestsResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }

    suspend fun fetchTransactionById(request: FetchTransactionByIdRequest): Response<FetchTransactionByIdResponse> {
        try {
            val response = httpClient.get(
                API_HOST + fetchTransactionByIdPath(
                    request.bankId,
                    request.accountId,
                    request.transactionId
                )
            ) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
            }.body<FetchTransactionByIdResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }

    suspend fun fetchTransactions(request: FetchTransactionsRequest): Response<FetchTransactionsResponse> {
        try {
            val response = httpClient.get(
                API_HOST + fetchTransactionsPath(
                    request.bankId,
                    request.accountId,
                    request.sort,
                    request.limit,
                    request.offset,
                    request.fromDate,
                    request.toDate
                )
            ) {
                buildHeaders {
                    append("Authorization", "DirectLogin token=${request.token}")
                }
            }.body<FetchTransactionsResponse>()
            return Response.Success(response)
        } catch (exp: ClientRequestException) {
            return Response.Error(exp)
        } catch (exp: Exception) {
            return Response.Error(exp)
        }
    }
}
