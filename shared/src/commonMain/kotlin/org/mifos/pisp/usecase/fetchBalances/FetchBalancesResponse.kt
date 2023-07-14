package org.mifos.pisp.usecase.fetchBalances

import kotlinx.serialization.SerialName

class FetchBalancesResponse(
    @SerialName("accounts") val accountBalances: List<AccountBalance>
)

class AccountBalance(
    val accountId: String,
    val currency: String,
    val amount: Double
)
