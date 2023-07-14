package org.mifos.pisp.usecase.loginClient

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class LoginClientUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<LoginClientRequest, LoginClientResponse>() {

    override suspend fun run(request: LoginClientRequest): Response<LoginClientResponse> {
        return repository.loginClient(request)
    }
}
