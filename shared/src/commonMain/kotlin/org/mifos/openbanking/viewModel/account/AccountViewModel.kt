package org.mifos.openbanking.viewModel.account

import kotlinx.coroutines.flow.MutableStateFlow
import org.mifos.openbanking.base.Response
import org.mifos.openbanking.coroutines.launchSilent
import org.mifos.openbanking.data.datasources.disk.DiskDataSource
import org.mifos.openbanking.domain.usecase.fetchAccounts.FetchAccountsRequest
import org.mifos.openbanking.domain.usecase.fetchAccounts.FetchAccountsResponse
import org.mifos.openbanking.domain.usecase.fetchAccounts.FetchAccountsUseCase
import org.mifos.openbanking.domain.usecase.fetchBalances.FetchBalancesRequest
import org.mifos.openbanking.domain.usecase.fetchBalances.FetchBalancesResponse
import org.mifos.openbanking.domain.usecase.fetchBalances.FetchBalancesUseCase
import org.mifos.openbanking.viewModel.base.BaseViewModel
import org.mifos.openbanking.viewModel.model.AccountModel
import kotlin.coroutines.CoroutineContext

class AccountViewModel(
    private val diskDataSource: DiskDataSource,
    private val coroutineContext: CoroutineContext,
    private val fetchAccountsUseCase: FetchAccountsUseCase,
    private val fetchBalancesUseCase: FetchBalancesUseCase,
) : BaseViewModel() {

    // LIVE DATA
    val accountStateLiveData = MutableStateFlow<AccountState>(LoadingAccountState)

    fun fetchAccounts() = launchSilent(
        coroutineContext,
        exceptionHandler,
        job
    ) {
        val request =
            FetchAccountsRequest(
                diskDataSource.getUserModel().token
            )

        val response = fetchAccountsUseCase.execute(request)

        if (response is Response.Success<FetchAccountsResponse>) {
            val supportedBanks = diskDataSource.getSupportedBanks()
            val accountModelList: MutableList<AccountModel> =
                mutableListOf()
            val banksConnected: MutableSet<String> = mutableSetOf()
            for (account in response.data.accounts) {
                accountModelList.add(
                    AccountModel(
                        account.accountId,
                        supportedBanks.find { bank -> bank.id == account.bankId }!!.shortName!!,
                        account.bankId
                    )
                )
                banksConnected.add(account.bankId)
            }
            val userModel = diskDataSource.getUserModel()
            userModel.accounts = accountModelList
            userModel.banksConnected = banksConnected
            diskDataSource.saveUserModel(userModel)
            accountStateLiveData.value = SuccessAccountState(accountModelList)
        } else if (response is Response.Error) {
            accountStateLiveData.value = ErrorAccountState(response.message)
        }
    }

    fun fetchBalances() = launchSilent(
        coroutineContext,
        exceptionHandler,
        job
    ) {
        val userModel = diskDataSource.getUserModel()
        for (bankId in userModel.banksConnected!!) {
            val response =
                fetchBalancesUseCase.execute(
                    FetchBalancesRequest(
                        bankId,
                        userModel.token
                    )
                )
            if (response is Response.Success<FetchBalancesResponse>) {
                for (accountBalances in response.data.accountBalances) {
                    val accountModel = userModel.accounts?.find { accountModel ->
                        accountBalances.accountId == accountModel.accountId
                    }
                    if (accountModel != null) {
                        accountModel.balance = accountBalances.amount
                        accountModel.currency = accountBalances.currency
                    }
                }
            } else if (response is Response.Error) {
                accountStateLiveData.value = ErrorAccountState(response.message)
                return@launchSilent
            }
        }

        diskDataSource.saveUserModel(userModel)
        accountStateLiveData.value = SuccessAccountState(userModel.accounts!!, true)
    }
}
