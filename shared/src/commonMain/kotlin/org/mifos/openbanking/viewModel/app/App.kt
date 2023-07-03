package org.mifos.openbanking.viewModel.app

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import org.mifos.openbanking.ApplicationDispatcher
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.coroutines.launchSilent
import org.mifos.openbanking.data.datasources.disk.DiskDataSource
import org.mifos.openbanking.domain.usecase.fetchBanks.FetchBanksRequest
import org.mifos.openbanking.domain.usecase.fetchBanks.FetchBanksResponse
import org.mifos.openbanking.domain.usecase.fetchBanks.FetchBanksUseCase

object App {

    val supportedBanksLiveData = MutableStateFlow<SupportedBanksState>(LoadingSupportedBanksState)
    private val fetchBanksUseCase = FetchBanksUseCase()

    private val coroutineContext = ApplicationDispatcher
    private val job: Job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    private val diskDataSource = DiskDataSource()

    fun appLaunch() {
        fetchBanks()
    }

    private fun fetchBanks() = launchSilent(coroutineContext, exceptionHandler, job) {
        val response = fetchBanksUseCase.execute(FetchBanksRequest())
        if (response is Response.Success<FetchBanksResponse>) {
            val bankList = response.data.bankList
            val sortedBy = bankList.sortedBy { it.shortName }
            diskDataSource.saveSupportedBanks(sortedBy)
            supportedBanksLiveData.value = SuccessSupportedBanksState
        } else if (response is Response.Error) {
            supportedBanksLiveData.value = ErrorSupportedBanksState(response.message)
        }
    }
}
