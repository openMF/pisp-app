package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class CredentialRegistrationResponse(
    val credential: Credential,
    val currentState: String
)

@Serializable
data class Credential(
    val status: String
)