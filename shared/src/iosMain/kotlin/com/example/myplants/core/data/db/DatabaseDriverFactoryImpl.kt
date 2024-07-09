package com.example.myplants.core.data.db

import com.example.myplants.PlantDatabase
import com.example.myplants.core.data.db.DatabaseDriverFactory
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
