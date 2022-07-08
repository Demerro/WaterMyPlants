package com.dewerro.watermyplants.presentation

import com.dewerro.watermyplants.domain.model.Plant

sealed class MainEvent {
    data class GetPlant(val id: Int) : MainEvent()
    data class InsertPlant(val plant: Plant) : MainEvent()
    data class DeletePlant(val plant: Plant) : MainEvent()
}
