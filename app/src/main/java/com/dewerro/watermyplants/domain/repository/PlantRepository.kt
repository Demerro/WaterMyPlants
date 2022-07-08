package com.dewerro.watermyplants.domain.repository

import com.dewerro.watermyplants.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    fun getAllPlants(): Flow<List<Plant>>

    suspend fun getPlant(id: Int): Plant

    suspend fun insertPlant(plant: Plant)

    suspend fun deletePlant(plant: Plant)
}