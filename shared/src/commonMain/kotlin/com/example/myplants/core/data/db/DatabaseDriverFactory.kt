package com.example.myplants.core.data.db

import com.squareup.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {
    fun create(): SqlDriver
}
