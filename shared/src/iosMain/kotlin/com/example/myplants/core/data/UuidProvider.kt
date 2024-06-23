package com.example.myplants.core.data

import platform.Foundation.NSUUID

actual object UuidProvider {
    actual fun getUuid(): String {
        return NSUUID.UUID().UUIDString()
    }
}
