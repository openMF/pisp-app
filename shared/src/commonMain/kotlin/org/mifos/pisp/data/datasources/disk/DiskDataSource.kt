package org.mifos.pisp.data.datasources.disk

import org.mifos.openbanking.Database
import org.mifos.pisp.data.datasources.disk.preferencesHelper.PreferencesHelper
import org.mifos.pisp.usecase.fetchBanks.Bank
import org.mifos.pisp.viewModel.model.UserModel

class DiskDataSource(
    private val preferencesHelper: PreferencesHelper,
    private val database: Database,
) {

    fun isUserLoggedIn(): Boolean {
        val userModel = preferencesHelper.getUserModel()
        return userModel != null
    }

    fun getUserModel(): UserModel {
        return preferencesHelper.getUserModel()!!
    }

    fun saveUserModel(userModel: UserModel) {
        preferencesHelper.saveUserModel(userModel)
    }

    fun saveSupportedBanks(bankList: List<Bank>) {
        preferencesHelper.saveSupportedBanks(bankList)
    }

    fun getSupportedBanks(): List<Bank> {
        return preferencesHelper.getSupportedBanks()
    }
}
