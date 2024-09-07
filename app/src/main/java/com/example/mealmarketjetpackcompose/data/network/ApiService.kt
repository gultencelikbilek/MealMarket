package com.example.mealmarketjetpackcompose.data.network

import com.example.mealmarketjetpackcompose.domain.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET
    suspend fun getMealList() : Response<MealResponse>
}