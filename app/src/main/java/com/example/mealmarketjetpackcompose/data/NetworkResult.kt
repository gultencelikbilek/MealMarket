package com.example.mealmarketjetpackcompose.data

import com.example.mealmarketjetpackcompose.domain.model.MealResponse

sealed class NetworkResult< out T> {
    data class Succes<T>(val data : T) : NetworkResult<T>()
    data class Error<Nothing>( val message : String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}