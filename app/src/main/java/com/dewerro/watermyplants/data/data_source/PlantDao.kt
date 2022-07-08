package com.dewerro.watermyplants.data.data_source

import androidx.room.*
import com.dewerro.watermyplants.domain.model.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant_table")
    fun getAllPlants(): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: Plant)

    @Query("SELECT * FROM plant_table WHERE id = :id")
    suspend fun getPlantById(id: Int): Plant

    @Delete
    suspend fun deletePlant(plant: Plant)
}