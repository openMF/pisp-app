package org.mifos.openbanking.data.datasources.disk

import org.mifos.openbanking.Database
import org.mifos.openbanking.disk.DriverFactory

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(driver)

    // Do more work with the database (see below).
    return database
}
