package org.mifos.pisp.usecase.fetchAccounts

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchAccountsUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchAccountsRequest, FetchAccountsResponse>() {

    override suspend fun run(request: FetchAccountsRequest): Response<FetchAccountsResponse> {
        return repository.fetchAccounts(request)
    }
}
