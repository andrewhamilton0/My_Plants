package com.example.myplants.core.data

import com.example.myplants.PlantDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

class DatabaseDriverFactoryImpl() : DatabaseDriverFactory {
    override fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = PlantDatabase.Schema,
            name = "plant.db"
        )
    }
}
