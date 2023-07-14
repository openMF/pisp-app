package org.mifos.pisp

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.mifos.openbanking.Database
import org.mifos.pisp.disk.DriverFactory

class DriverFactoryImpl(private val context: Context) : DriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, context, "openbanking.db")
    }
}
