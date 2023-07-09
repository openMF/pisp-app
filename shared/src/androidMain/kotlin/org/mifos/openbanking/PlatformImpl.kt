package org.mifos.openbanking

class PlatformImpl : Platform {
    override val name: String
        get() = "Android ${android.os.Build.VERSION.SDK_INT}"
}
