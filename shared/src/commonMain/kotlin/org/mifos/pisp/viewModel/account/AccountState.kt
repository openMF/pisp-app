package org.mifos.pisp.viewModel.account

import org.mifos.pisp.viewModel.model.AccountModel

sealed class AccountState

class SuccessAccountState(
    val accountList: List<AccountModel>,
    val balancesFetched: Boolean = false
) : AccountState()

object LoadingAccountState : AccountState()
class ErrorAccountState(val message: String?) : AccountState()