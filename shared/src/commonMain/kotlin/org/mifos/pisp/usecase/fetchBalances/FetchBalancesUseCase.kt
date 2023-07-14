package org.mifos.pisp.usecase.fetchBalances

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchBalancesUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchBalancesRequest, FetchBalancesResponse>() {

    override suspend fun run(request: FetchBalancesRequest): Response<FetchBalancesResponse> {
        return repository.fetchBalances(request)
    }
}
