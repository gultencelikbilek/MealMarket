package com.example.mealmarketjetpackcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meal(
    val name: String = "",
    val price: String ="",
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val imageName: String =""
)