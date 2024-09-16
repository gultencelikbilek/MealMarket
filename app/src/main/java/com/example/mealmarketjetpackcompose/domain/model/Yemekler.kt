package com.example.mealmarketjetpackcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Yemekler(
    val yemek_adi: String ,
    val yemek_fiyat: String,
    @PrimaryKey
    val yemek_id: String ,
    val yemek_resim_adi: String
)