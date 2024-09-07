package com.example.mealmarketjetpackcompose.domain.repo

import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.domain.model.MealResponse

interface MealRepository {
    suspend fun getMeal() : NetworkResult<MealResponse>
}