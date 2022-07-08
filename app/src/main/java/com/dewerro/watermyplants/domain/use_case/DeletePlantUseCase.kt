package com.dewerro.watermyplants.domain.use_case

import com.dewerro.watermyplants.domain.model.Plant
import com.dewerro.watermyplants.domain.repository.PlantRepository

class DeletePlantUseCase(private val repository: PlantRepository) {
    suspend operator fun invoke(plant: Plant) {
        repository.deletePlant(plant)
    }
}