package org.mifos.pisp.usecase.createTransactionRequest

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class CreateTransactionRequestUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<CreateTransactionRequestRequest, CreateTransactionRequestResponse>() {

    override suspend fun run(request: CreateTransactionRequestRequest): Response<CreateTransactionRequestResponse> {
        return repository.createTransactionRequest(request)
    }
}
