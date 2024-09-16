package com.example.mealmarketjetpackcompose.data.di.repoimpl

import android.content.Context
import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.data.di.AppModule
import com.example.mealmarketjetpackcompose.domain.model.Meal
import com.example.mealmarketjetpackcompose.domain.model.Yemekler
import com.example.mealmarketjetpackcompose.domain.repo.MealRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(@ApplicationContext context: Context) :
    MealRepository {
    val apiService = AppModule.providesRetrofit(context)
    val favoriteDao = AppModule.provideRoom(context).favoriteDao()
    override suspend fun getMeal(): NetworkResult<List<Yemekler>> {
        return try {
            val response = apiService.getMealList()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Succes(body.yemekler)
                } else {
                    NetworkResult.Error("Response body is null")
                }
            } else {
                NetworkResult.Error("Response is not successful: ${response.message()}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message.toString())
        }
    }

    override suspend fun insertFavorite(yemekler: Yemekler) {
        favoriteDao.insertFavorite(yemekler)
    }

    override suspend fun deleteFavorite(yemekler: Yemekler) {
        favoriteDao.deleteFavorite(yemekler)
    }

    override suspend fun getAllFavorite(): List<Yemekler> {
        return favoriteDao.getAllFavorite()
    }

    override suspend fun getByNameFavorite(yemek_adi: String): Yemekler {
        return favoriteDao.getByNameFavori(yemek_adi)
    }
}