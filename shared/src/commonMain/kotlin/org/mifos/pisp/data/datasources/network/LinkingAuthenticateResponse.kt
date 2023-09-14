package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class LinkingAuthenticateResponse(
    val consentId: String,
    val consentRequestId: String,
    val status: String,
    val scopes: List<Scope>
)

@Serializable
data class Scope(
    val accountId: String,
    val actions: List<String>
)