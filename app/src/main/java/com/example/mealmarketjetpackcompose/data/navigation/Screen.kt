package com.example.mealmarketjetpackcompose.data.navigation

sealed class Screen(val route : String){
    object MealMarketScreen : Screen("meal_market_screen")
    object FavoriteScreen : Screen("favorite_screen")
    object CartScreen : Screen("cart_screen")
}