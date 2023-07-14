package org.mifos.pisp.usecase.fetchAccounts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class FetchAccountsResponse(
    @SerialName("accounts") val accounts: List<Account>
)

@Serializable
data class Account(
    @SerialName("id")
    val accountId: String,
    @SerialName("bank_id")
    val bankId: String
)
