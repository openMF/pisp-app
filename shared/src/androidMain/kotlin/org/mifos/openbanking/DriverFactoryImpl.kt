package org.mifos.openbanking

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.mifos.openbanking.disk.DriverFactory

class DriverFactoryImpl(private val context: Context) : DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "openbanking.db")
    }
}
