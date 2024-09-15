package com.example.mealmarketjetpackcompose.data.network

import com.example.mealmarketjetpackcompose.data.Constants
import com.example.mealmarketjetpackcompose.domain.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getMealList() : Response<MealResponse>
}