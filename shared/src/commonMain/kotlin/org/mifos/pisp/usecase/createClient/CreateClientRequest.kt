package org.mifos.pisp.usecase.createClient

import org.mifos.pisp.usecase.base.BaseRequest

class CreateClientRequest(
    val name: String,
    val contact: String,
    val scopes: ArrayList<String>,
    val redirectUris: ArrayList<String>
) : BaseRequest {

    override fun validate(): Boolean {
        // TODO
        return true
    }

}