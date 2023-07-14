package org.mifos.pisp.usecase.loginClient

import org.mifos.pisp.usecase.base.BaseRequest

class LoginClientRequest(
    val username: String,
    val password: String,
    val consumer_key: String
) : BaseRequest {

    override fun validate(): Boolean {
        // TODO
        return true
    }

}