package com.example.mealmarketjetpackcompose.domain.repo

import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.domain.model.Meal
import com.example.mealmarketjetpackcompose.domain.model.Yemekler

interface MealRepository {
    suspend fun getMeal() : NetworkResult<List<Yemekler>>
    suspend fun insertFavorite(yemekler: Yemekler)
    suspend fun deleteFavorite(yemekler: Yemekler)
    suspend fun getAllFavorite() : List<Yemekler>
    suspend fun getByNameFavorite(yemek_adi : String) : Yemekler

}