package org.mifos.pisp.usecase.createClient

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class CreateClientUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<CreateClientRequest, CreateClientResponse>() {

    override suspend fun run(request: CreateClientRequest): Response<CreateClientResponse> {
        return repository.createClient(request)
    }
}
