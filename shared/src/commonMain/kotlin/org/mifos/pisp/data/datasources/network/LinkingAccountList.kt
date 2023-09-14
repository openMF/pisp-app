package org.mifos.pisp.data.datasources.network

import kotlinx.serialization.Serializable

@Serializable
data class LinkingAccountList(
    val accountNickName: String,
    val id: String,
    val currency: String
)