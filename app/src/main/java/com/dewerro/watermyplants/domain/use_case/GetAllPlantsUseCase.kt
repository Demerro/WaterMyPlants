package com.dewerro.watermyplants.domain.use_case

import com.dewerro.watermyplants.domain.model.Plant
import com.dewerro.watermyplants.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow

class GetAllPlantsUseCase(private val repository: PlantRepository) {
    operator fun invoke(): Flow<List<Plant>> = repository.getAllPlants()
}