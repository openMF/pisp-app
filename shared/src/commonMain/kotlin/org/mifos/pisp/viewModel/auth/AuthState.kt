package org.mifos.pisp.viewModel.auth

sealed class AuthState

object SuccessAuthState : AuthState()
object LoadingAuthState : AuthState()
class ErrorAuthState(val message: String?) : AuthState()