package com.example.mealmarketjetpackcompose.data.di.repoimpl

import android.content.Context
import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.data.di.AppModule
import com.example.mealmarketjetpackcompose.domain.model.MealResponse
import com.example.mealmarketjetpackcompose.domain.repo.MealRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(@ApplicationContext context : Context) : MealRepository {
    val apiService = AppModule.providesRetrofit(context)
    override suspend fun getMeal(): NetworkResult<MealResponse> {
        return try {
            val response = apiService.getMealList()
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Succes(it)
                } ?: NetworkResult.Error("Body is null")
            } else {
                NetworkResult.Error("Response is not successful")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message.toString())
        }
    }
}