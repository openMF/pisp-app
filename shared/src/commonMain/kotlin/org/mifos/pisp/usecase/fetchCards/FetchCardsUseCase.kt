package org.mifos.pisp.usecase.fetchCards

import org.mifos.pisp.base.Response
import org.mifos.pisp.data.repository.OpenBankingRepository
import org.mifos.pisp.usecase.base.BaseUseCase

class FetchCardsUseCase(private val repository: OpenBankingRepository) :
    BaseUseCase<FetchCardsRequest, FetchCardsResponse>() {

    override suspend fun run(request: FetchCardsRequest): Response<FetchCardsResponse> {
        return repository.fetchCards(request)
    }
}
