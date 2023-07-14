package org.mifos.pisp.viewModel.app

sealed class SupportedBanksState

object SuccessSupportedBanksState : SupportedBanksState()
object LoadingSupportedBanksState : SupportedBanksState()
class ErrorSupportedBanksState(val message: String?) : SupportedBanksState()