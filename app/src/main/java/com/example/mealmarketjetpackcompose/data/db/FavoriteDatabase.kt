package com.example.mealmarketjetpackcompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mealmarketjetpackcompose.domain.model.Meal
import com.example.mealmarketjetpackcompose.domain.model.Yemekler

@Database(entities = [Yemekler::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao
}