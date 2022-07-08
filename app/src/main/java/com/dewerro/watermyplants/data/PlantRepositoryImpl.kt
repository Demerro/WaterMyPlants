package com.dewerro.watermyplants.data

import com.dewerro.watermyplants.data.data_source.PlantDao
import com.dewerro.watermyplants.domain.model.Plant
import com.dewerro.watermyplants.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class PlantRepositoryImpl(private val dao: PlantDao) : PlantRepository {
    override fun getAllPlants(): Flow<List<Plant>> = dao.getAllPlants()

    override suspend fun getPlant(id: Int): Plant = dao.getPlantById(id)

    override suspend fun insertPlant(plant: Plant) {
        dao.insertPlant(plant)
    }

    override suspend fun deletePlant(plant: Plant) {
        dao.deletePlant(plant)
    }
}