package org.mifos.openbanking

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.mifos.openbanking.disk.DriverFactory

class DriverFactoryImpl : DriverFactory {
    override fun createDriver(): SqlDriver {
        val database = NativeSqliteDriver(Database.Schema, "openbanking.db")
        return database
    }
}
