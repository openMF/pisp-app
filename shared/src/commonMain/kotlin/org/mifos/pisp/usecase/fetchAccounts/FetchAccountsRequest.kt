package org.mifos.pisp.usecase.fetchAccounts

import org.mifos.pisp.usecase.base.BaseRequest

class FetchAccountsRequest(val token: String?) :
    BaseRequest {

    override fun validate(): Boolean {
        return token != null
    }

}