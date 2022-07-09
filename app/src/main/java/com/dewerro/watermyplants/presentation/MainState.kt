package com.dewerro.watermyplants.presentation

import com.dewerro.watermyplants.domain.model.Plant

data class MainState(
    val plantList: List<Plant> = emptyList(),
    val categoryList: List<String> = emptyList()
)