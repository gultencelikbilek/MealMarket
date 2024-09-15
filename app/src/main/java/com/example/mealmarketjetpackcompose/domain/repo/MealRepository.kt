package com.example.mealmarketjetpackcompose.domain.repo

import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.domain.model.Yemekler

interface MealRepository {
    suspend fun getMeal() : NetworkResult<List<Yemekler>>
}