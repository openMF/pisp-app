package org.mifos.openbanking.viewModel.transaction

import kotlinx.coroutines.flow.MutableStateFlow
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.coroutines.launchSilent
import org.mifos.openbanking.data.datasources.disk.DiskDataSource
import org.mifos.openbanking.domain.usecase.createTransactionRequest.CreateTransactionRequestRequest
import org.mifos.openbanking.domain.usecase.createTransactionRequest.CreateTransactionRequestUseCase
import org.mifos.openbanking.domain.usecase.fetchBanks.Bank
import org.mifos.openbanking.domain.usecase.fetchTransactionRequests.FetchTransactionRequestsRequest
import org.mifos.openbanking.domain.usecase.fetchTransactionRequests.FetchTransactionRequestsUseCase
import org.mifos.openbanking.viewModel.base.BaseViewModel
import org.mifos.openbanking.viewModel.model.TransactionRequestModel
import kotlin.coroutines.CoroutineContext

class TransactionViewModel(
    private val coroutineContext: CoroutineContext,
    private val diskDataSource: DiskDataSource,
    private val createTransactionRequestUseCase: CreateTransactionRequestUseCase,
    private val fetchTransactionRequestsUseCase: FetchTransactionRequestsUseCase,
) : BaseViewModel() {

    // LIVE DATA
    val createTransactionRequestStateLiveData = MutableStateFlow<CreateTransactionRequestState>(
        LoadingCreateTransactionRequestState
    )
    val fetchTransactionStateLiveData = MutableStateFlow<FetchTransactionState>(
        LoadingFetchTransactionState
    )

    fun fetchTransactionRequestsFor(bankId: String, accountId: String) = launchSilent(
        coroutineContext,
        exceptionHandler,
        job
    ) {
        val userModel = diskDataSource.getUserModel()
        val request = FetchTransactionRequestsRequest(
            userModel.token,
            bankId,
            accountId
        )
        val response = fetchTransactionRequestsUseCase.execute(request)
        if (response is Response.Success) {
            val transactionModelList = response.data.transactionRequestsList
                .map {
                    TransactionRequestModel(
                        it.id,
                        it.type,
                        it.from.bankId,
                        it.from.accountId,
                        it.details.to.bankId,
                        it.details.to.accountId,
                        it.details.value.currency,
                        it.details.value.amount,
                        it.details.description
                    )
                }
            fetchTransactionStateLiveData.value = SuccessFetchTransactionState(transactionModelList)

        } else if (response is Response.Error) {
            fetchTransactionStateLiveData.value = ErrorFetchTransactionState(response.message)
        }
    }

    fun createTransactionRequest(
        sourceBankId: String,
        sourceAccountId: String,
        destinationBankId: String,
        destinationAccountId: String,
        currency: String,
        amount: Double,
        description: String
    ) = launchSilent(
        coroutineContext,
        exceptionHandler,
        job
    ) {
        val request =
            CreateTransactionRequestRequest(
                diskDataSource.getUserModel().token,
                sourceBankId,
                sourceAccountId,
                destinationBankId,
                destinationAccountId,
                currency,
                amount,
                description
            )

        val response = createTransactionRequestUseCase.execute(request)
        if (response is Response.Success) {
            createTransactionRequestStateLiveData.value = SuccessCreateTransactionRequestState
        } else if (response is Response.Error) {
            createTransactionRequestStateLiveData.value =
                ErrorCreateTransactionRequestState(response.message)
        }
    }

    fun getSupportedBanks(): List<Bank> {
        return diskDataSource.getSupportedBanks()
    }
}
