package com.example.mealmarketjetpackcompose.data.di.usecase

import android.util.Log
import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.data.di.repoimpl.MealRepositoryImpl
import com.example.mealmarketjetpackcompose.domain.model.MealResponse
import com.example.mealmarketjetpackcompose.domain.model.Yemekler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MealListUseCase @Inject constructor(private val mealRepositoryImpl: MealRepositoryImpl) {

    operator suspend fun invoke(): Flow<NetworkResult<List<Yemekler>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = mealRepositoryImpl.getMeal()
            emit(response)
            Log.d("mealListUseCase:",response.toString())
        }catch (e:Exception){
            emit(NetworkResult.Error(e.message.toString()))
        }
    }
}