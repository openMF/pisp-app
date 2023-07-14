package org.mifos.pisp.usecase.fetchCards

import org.mifos.pisp.usecase.base.BaseRequest


class FetchCardsRequest(
    val token: String
) :
    BaseRequest {

    override fun validate(): Boolean {
        return true
    }

}