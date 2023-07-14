package org.mifos.pisp.viewModel.transaction

import org.mifos.pisp.viewModel.model.TransactionRequestModel

sealed class FetchTransactionState

class SuccessFetchTransactionState(val transactionRequestModelList: List<TransactionRequestModel>) :
    FetchTransactionState()

object LoadingFetchTransactionState : FetchTransactionState()
class ErrorFetchTransactionState(val message: String?) : FetchTransactionState()