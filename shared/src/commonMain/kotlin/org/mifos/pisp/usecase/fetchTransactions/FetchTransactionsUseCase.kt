package org.mifos.pisp.usecase.fetchTransactions

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchTransactionsUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchTransactionsRequest, FetchTransactionsResponse>() {

    override suspend fun run(request: FetchTransactionsRequest): Response<FetchTransactionsResponse> {
        return repository.fetchTransactions(request)
    }
}
