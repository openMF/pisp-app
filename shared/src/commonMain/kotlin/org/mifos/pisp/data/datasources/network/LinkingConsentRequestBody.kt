package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class LinkingConsentRequestBody(
    val toParticipantId: String,
    val consentRequestId: String,
    val accounts: List<LinkingAccountList>,
    val actions: List<String>,
    val userId: String,
    val callbackUri: String
)