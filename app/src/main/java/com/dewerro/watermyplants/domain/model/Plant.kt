package com.dewerro.watermyplants.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey val id: Int? = null,
    val photoUriString: String,
    val type: String,
    val size: String,
    val humidity: Int,
    val light: String,
    val temperature: Int,
    val category: String,
    val plant: String,
    val wateringTime: String
)