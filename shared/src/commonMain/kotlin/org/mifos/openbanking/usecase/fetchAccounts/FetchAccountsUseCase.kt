package org.mifos.openbanking.domain.usecase.fetchAccounts

import org.mifos.openbanking.base.Response
import org.mifos.openbanking.data.repository.OpenBankingRepository
import org.mifos.openbanking.domain.usecase.base.BaseUseCase

class FetchAccountsUseCase(private val repository: OpenBankingRepository = OpenBankingRepository()) :
    BaseUseCase<FetchAccountsRequest, FetchAccountsResponse>() {

    override suspend fun run(request: FetchAccountsRequest): Response<FetchAccountsResponse> {
        return repository.fetchAccounts(request)
    }
}
