package com.example.mealmarketjetpackcompose.data.di.usecase

import com.example.mealmarketjetpackcompose.data.di.repoimpl.MealRepositoryImpl
import javax.inject.Inject

class GetByNameFavoriteUseCase @Inject constructor(private  val repositoryImpl: MealRepositoryImpl) {

    operator suspend fun invoke(yemek_adi : String) = repositoryImpl.getByNameFavorite(yemek_adi)
}