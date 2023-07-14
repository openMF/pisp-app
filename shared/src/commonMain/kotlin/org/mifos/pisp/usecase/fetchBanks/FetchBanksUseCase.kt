package org.mifos.pisp.usecase.fetchBanks

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchBanksUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchBanksRequest, FetchBanksResponse>() {

    override suspend fun run(request: FetchBanksRequest): Response<FetchBanksResponse> {
        return repository.fetchBanks(request)
    }
}
