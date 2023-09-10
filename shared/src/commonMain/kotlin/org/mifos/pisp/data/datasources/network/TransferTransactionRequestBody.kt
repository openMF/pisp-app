package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class TransferTransactionRequestBody(
    val consentId: String,
    val payee: Payee,
    val payer: Payer,
    val amountType: String,
    val amount: Amount,
    val transactionType: TransactionType,
    val expiration: String
)

@Serializable
data class Payer(
    val partyIdType: String,
    val partyIdentifier: String,
    val fspId: String
)

@Serializable
data class Amount(
    val amount: String,
    val currency: String
)

@Serializable
data class TransactionType(
    val scenario: String,
    val initiator: String,
    val initiatorType: String
)
