package com.example.mealmarketjetpackcompose.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mealmarketjetpackcompose.domain.model.Meal
import com.example.mealmarketjetpackcompose.domain.model.Yemekler

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(yemekler: Yemekler)

    @Delete
    suspend fun deleteFavorite(yemekler: Yemekler)

    @Query("SELECT * FROM yemekler")
    suspend fun getAllFavorite() : List<Yemekler>


    @Query("SELECT * FROM yemekler WHERE yemek_adi  = :yemek_adi")
    suspend fun getByNameFavori(yemek_adi  : String) : Yemekler
}