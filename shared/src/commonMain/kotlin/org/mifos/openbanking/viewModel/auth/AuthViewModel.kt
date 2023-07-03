package org.mifos.openbanking.viewModel.auth

import kotlinx.coroutines.flow.MutableStateFlow
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.coroutines.launchSilent
import org.mifos.openbanking.data.datasources.disk.DiskDataSource
import org.mifos.openbanking.data.datasources.network.CONSUMER_KEY
import org.mifos.openbanking.domain.usecase.loginClient.LoginClientRequest
import org.mifos.openbanking.domain.usecase.loginClient.LoginClientUseCase
import org.mifos.openbanking.viewModel.base.BaseViewModel
import org.mifos.openbanking.viewModel.model.UserModel
import kotlin.coroutines.CoroutineContext

class AuthViewModel(
    private val coroutineContext: CoroutineContext,
    private val diskDataSource: DiskDataSource,
    private val loginClientUseCase: LoginClientUseCase,
) : BaseViewModel() {

    // LIVE DATA
    val authStateLiveData = MutableStateFlow<AuthState>(LoadingAuthState)

    fun createClient() = launchSilent(
        coroutineContext,
        exceptionHandler,
        job
    ) {

    }

    fun loginClient(username: String, password: String) =
        launchSilent(
            coroutineContext,
            exceptionHandler,
            job
        ) {
            val request =
                LoginClientRequest(
                    username,
                    password,
                    CONSUMER_KEY
                )
            val response = loginClientUseCase.execute(request)

            if (response is Response.Success) {
                val userModel = UserModel(response.data.token, username)
                diskDataSource.saveUserModel(userModel)
                authStateLiveData.value = SuccessAuthState
            } else if (response is Response.Error) {
                authStateLiveData.value = ErrorAuthState(response.exception.message)
            }
        }

    fun isUserLoggedIn(): Boolean {
        return diskDataSource.isUserLoggedIn()
    }
}
