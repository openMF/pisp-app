package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
    data class LinkingConsentResponse(
        val channelResponse: ChannelResponse,
        val currentState: String
    )

    @Serializable
    data class ChannelResponse(
        val consentRequestId: String,
        val scopes: List<ChannelScope>,
        val authChannels: List<String>,
        val callbackUri: String
    )

    @Serializable
    data class ChannelScope(
        val accountId: String,
        val actions: List<String>
    )