package org.mifos.openbanking

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform