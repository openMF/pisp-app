package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class TransferApproveRequestBody(
    val responseType: String,
    val signedPayload: SignedPayload
)

@Serializable
data class SignedPayload(
    val signedPayloadType: String,
    val fidoSignedPayload: FidoSignedPayload
)

@Serializable
data class FidoSignedPayload(
    val id: String,
    val rawId: String,
    val response: Response,
    val type: String
)

@Serializable
data class Response(
    val authenticatorData: String,
    val clientDataJSON: String,
    val signature: String
)
