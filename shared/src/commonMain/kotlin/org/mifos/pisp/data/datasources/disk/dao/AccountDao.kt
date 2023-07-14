package org.mifos.pisp.data.datasources.disk.dao

import org.mifos.openbanking.Database
import org.mifos.pisp.AccountModel

class AccountDao(database: Database) {

    private val db = database.accountModelQueries

    internal fun insert(accountId: String, bankId: String) {
        db.insertItem(
            accoundId = accountId,
            bankId = bankId
        )
    }

    internal fun select(): List<AccountModel> = db.selectAll().executeAsList()
}
