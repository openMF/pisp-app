package org.mifos.pisp.usecase.fetchTransactionById

import org.mifos.pisp.usecase.base.BaseRequest


class FetchTransactionByIdRequest(
    val token: String,
    val bankId: String,
    val accountId: String,
    val transactionId: String
) :
    BaseRequest {

    override fun validate(): Boolean {
        return true
    }

}