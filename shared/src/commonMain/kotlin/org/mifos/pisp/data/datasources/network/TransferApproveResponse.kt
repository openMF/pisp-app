package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class TransferApproveResponse(
    val transactionStatus: TransactionStatus,
    val currentState: String
)

@Serializable
data class TransactionStatus(
    val transactionRequestState: String,
    val transactionState: String
)
