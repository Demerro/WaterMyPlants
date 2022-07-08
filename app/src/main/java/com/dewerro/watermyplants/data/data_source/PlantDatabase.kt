package com.dewerro.watermyplants.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dewerro.watermyplants.domain.model.Plant

@Database(
    entities = [Plant::class],
    version = 1
)
abstract class PlantDatabase : RoomDatabase() {
    abstract val plantDao: PlantDao

    companion object {
        const val DATABASE_NAME = "plant_db"
    }
}