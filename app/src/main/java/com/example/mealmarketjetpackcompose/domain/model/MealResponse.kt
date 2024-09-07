package com.example.mealmarketjetpackcompose.domain.model

data class MealResponse(
    val success: Int,
    val yemekler: List<Yemekler>
)