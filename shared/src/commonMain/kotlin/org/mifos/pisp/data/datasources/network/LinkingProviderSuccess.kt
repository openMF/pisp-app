package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class LinkingProviderSuccess(
    val providers: List<String>,
    val currentState: String
)