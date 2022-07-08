package com.dewerro.watermyplants.domain.use_case

data class PlantUseCases(
    val getAllPlantsUseCase: GetAllPlantsUseCase,
    val getPlantUseCase: GetPlantUseCase,
    val insertPlantUseCase: InsertPlantUseCase,
    val deletePlantUseCase: DeletePlantUseCase
)