package org.mifos.pisp.data.datasources.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransfersApi(private val httpClient: HttpClient) {

    // PISP Transfers: Party Lookup
    @OptIn(InternalAPI::class)
    suspend fun fetchPartyInformation(
        transactionRequestId: String,
        payee: Payee
    ): Flow<TransferPartyLookupResponse> {
        return flow {
            val endpoint = OUTBOUND_SERVER + PARTY_LOOKUP_PATH
            val requestBody = TransferPartyLookupRequestBody(
                transactionRequestId = transactionRequestId,
                payee = payee
            )
            val requestBuilder = HttpRequestBuilder()
            requestBuilder.url(endpoint)
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.body = requestBody

            val response = httpClient.post(requestBuilder)
            val result = response.body<TransferPartyLookupResponse>()
            emit(result)
        }
    }

    // PISP Transfers: Initiate Transfer Transaction
    @OptIn(InternalAPI::class)
    suspend fun processTransferTransaction(
        transactionRequestId: String,
        consentId: String,
        payee: Payee,
        payer: Payer,
        amountType: String,
        amount: Amount,
        transactionType: TransactionType,
        expiration: String
    ): Flow<TransferTransactionResponse> {
        return flow {
            val endpoint = OUTBOUND_SERVER + createTransferTransactionPath(transactionRequestId)
            val requestBody = TransferTransactionRequestBody(
                consentId = consentId,
                payee = payee,
                payer = payer,
                amountType = amountType,
                amount = amount,
                transactionType = transactionType,
                expiration = expiration
            )
            val requestBuilder = HttpRequestBuilder()
            requestBuilder.url(endpoint)
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.body = requestBody

            val response = httpClient.post(requestBuilder)
            val result = response.body<TransferTransactionResponse>()
            emit(result)
        }
    }

    // PISP Transfers: Approve Transfer Transaction
    @OptIn(InternalAPI::class)
    suspend fun processTransferApprove(
        transactionRequestId: String,
        consentId: String,
        payee: Payee,
        payer: Payer,
        amountType: String,
        amount: Amount,
        transactionType: TransactionType,
        expiration: String
    ): Flow<TransferApproveResponse> {
        return flow {
            val endpoint = OUTBOUND_SERVER + createTransferApprovePath(transactionRequestId)
            val requestBody = TransferTransactionRequestBody(
                consentId = consentId,
                payee = payee,
                payer = payer,
                amountType = amountType,
                amount = amount,
                transactionType = transactionType,
                expiration = expiration
            )

            val requestBuilder = HttpRequestBuilder()
            requestBuilder.url(endpoint)
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.body = requestBody

            val response = httpClient.post(requestBuilder)
            val result = response.body<TransferApproveResponse>()
            emit(result)
        }
    }
}