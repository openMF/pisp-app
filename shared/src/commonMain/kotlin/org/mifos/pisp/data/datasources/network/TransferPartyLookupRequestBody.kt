package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class TransferPartyLookupRequestBody(
    val transactionRequestId: String,
    val payee: Payee
)

@Serializable
data class Payee(
    val partyIdType: String,
    val partyIdentifier: String,
    val fspId: String
)

