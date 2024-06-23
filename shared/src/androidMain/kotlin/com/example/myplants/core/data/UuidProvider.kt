package com.example.myplants.core.data

import java.util.UUID

actual object UuidProvider {
    actual fun getUuid(): String {
        return UUID.randomUUID().toString()
    }
}
