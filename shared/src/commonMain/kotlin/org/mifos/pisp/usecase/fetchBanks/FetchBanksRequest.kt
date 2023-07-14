package org.mifos.pisp.usecase.fetchBanks

import org.mifos.pisp.usecase.base.BaseRequest


class FetchBanksRequest() :
    BaseRequest {

    override fun validate(): Boolean {
        return true
    }

}