package org.mifos.pisp.usecase.fetchTransactionRequests

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchTransactionRequestsUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchTransactionRequestsRequest, FetchTransactionRequestsResponse>() {

    override suspend fun run(request: FetchTransactionRequestsRequest): Response<FetchTransactionRequestsResponse> {
        return repository.fetchTransactionRequests(request)
    }
}
