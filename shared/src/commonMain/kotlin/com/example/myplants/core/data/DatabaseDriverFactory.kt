package com.example.myplants.core.data

import com.squareup.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun create(): SqlDriver
}
