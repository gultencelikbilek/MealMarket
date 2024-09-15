package com.example.mealmarketjetpackcompose.feature.home_list_screen

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MealMarketScreen(
    navController: NavController,
    homeViewModel : HomeViewModel = hiltViewModel()
) {
    val homeState = homeViewModel.mealResponse.collectAsState()
    LaunchedEffect(homeState) {
        homeViewModel.mealList()
        Log.d("succes:",homeViewModel.mealList().toString())
    }

}