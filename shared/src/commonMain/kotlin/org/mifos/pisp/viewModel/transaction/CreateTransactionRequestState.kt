package org.mifos.pisp.viewModel.transaction


sealed class CreateTransactionRequestState

object SuccessCreateTransactionRequestState : CreateTransactionRequestState()
object LoadingCreateTransactionRequestState : CreateTransactionRequestState()
class ErrorCreateTransactionRequestState(val message: String?) : CreateTransactionRequestState()