package com.example.mealmarketjetpackcompose.data.di.usecase

import com.example.mealmarketjetpackcompose.data.di.repoimpl.MealRepositoryImpl
import com.example.mealmarketjetpackcompose.domain.model.Yemekler
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(private val repositoryImpl: MealRepositoryImpl) {

    operator suspend fun invoke(yemekler: Yemekler) = repositoryImpl.deleteFavorite(yemekler)
}