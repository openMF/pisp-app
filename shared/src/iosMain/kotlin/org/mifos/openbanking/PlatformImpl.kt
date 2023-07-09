package org.mifos.openbanking

import platform.UIKit.UIDevice

class PlatformImpl : Platform {
    override val name: String
        get() = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

}
