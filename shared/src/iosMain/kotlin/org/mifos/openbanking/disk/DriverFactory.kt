package org.mifos.openbanking.disk

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.mifos.openbanking.Database

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val database = NativeSqliteDriver(Database.Schema, "openbanking.db")
        return database
    }
}
