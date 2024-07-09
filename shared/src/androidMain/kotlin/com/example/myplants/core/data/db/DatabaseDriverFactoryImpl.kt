package com.example.myplants.core.data.db

import android.content.Context
import com.example.myplants.PlantDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

class DatabaseDriverFactoryImpl(
    private val context: Context
) : DatabaseDriverFactory {
    override fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = PlantDatabase.Schema,
            context = context,
            name = "plant.db"
        )
    }
}
