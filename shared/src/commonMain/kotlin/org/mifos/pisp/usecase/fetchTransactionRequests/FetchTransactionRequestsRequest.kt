package org.mifos.pisp.usecase.fetchTransactionRequests

import org.mifos.pisp.usecase.base.BaseRequest


class FetchTransactionRequestsRequest(
    val token: String,
    val bankId: String,
    val accountId: String
) :
    BaseRequest {

    override fun validate(): Boolean {
        return true
    }

}