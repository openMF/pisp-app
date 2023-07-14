package org.mifos.pisp.usecase.loginClient

import kotlinx.serialization.Serializable

@Serializable
class LoginClientResponse(val token: String)