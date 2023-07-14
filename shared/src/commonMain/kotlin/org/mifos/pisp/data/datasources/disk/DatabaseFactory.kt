package org.mifos.pisp.data.datasources.disk

import org.mifos.openbanking.Database
import org.mifos.pisp.disk.DriverFactory

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()

    // Do more work with the database (see below).
    return Database(driver)
}
