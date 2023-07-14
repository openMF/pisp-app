package org.mifos.pisp

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.mifos.openbanking.Database
import org.mifos.pisp.disk.DriverFactory

class DriverFactoryImpl : DriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "pisp.db")
    }
}
