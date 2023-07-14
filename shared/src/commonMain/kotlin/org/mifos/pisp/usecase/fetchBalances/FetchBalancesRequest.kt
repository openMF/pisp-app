package org.mifos.pisp.usecase.fetchBalances

import org.mifos.pisp.usecase.base.BaseRequest

class FetchBalancesRequest(val bankId: String, val token: String?) :
    BaseRequest {

    override fun validate(): Boolean {
        return token != null
    }

}