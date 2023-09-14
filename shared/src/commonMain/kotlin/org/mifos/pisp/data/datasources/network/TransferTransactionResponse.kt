package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class TransferTransactionResponse(
    val currentState: String,
    val authorization: Authorization
)

@Serializable
data class Authorization(
    val authenticationType: String,
    val retriesLeft: String,
    val amount: Amount,
    val transactionId: String,
    val transactionRequestId: String,
    val quote: Quote
)

@Serializable
data class Quote(
    val transferAmount: Amount,
    val expiration: String,
    val ilpPacket: String,
    val condition: String
)
