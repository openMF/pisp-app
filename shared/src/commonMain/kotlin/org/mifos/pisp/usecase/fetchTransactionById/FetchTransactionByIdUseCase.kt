package org.mifos.pisp.usecase.fetchTransactionById

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchTransactionByIdUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchTransactionByIdRequest, FetchTransactionByIdResponse>() {

    override suspend fun run(request: FetchTransactionByIdRequest): Response<FetchTransactionByIdResponse> {
        return repository.fetchTransactionById(request)
    }
}
