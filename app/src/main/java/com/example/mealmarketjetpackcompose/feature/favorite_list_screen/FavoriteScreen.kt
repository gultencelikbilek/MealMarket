package com.example.mealmarketjetpackcompose.feature.favorite_list_screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mealmarketjetpackcompose.feature.component.FavoriteCarComponent
import com.example.mealmarketjetpackcompose.feature.home_list_screen.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeStateFav = homeViewModel.favoriteMeals
    LaunchedEffect(Unit) {
        homeViewModel.getAllMeal()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Favoriler")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0XFF4B57A1)
                )
            )
        },
        content = {paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(paddingValues)

            ) {
                items(homeStateFav.value){yemek_id ->
                    FavoriteCarComponent(yemek_id)
                    Log.d("yemek_id",yemek_id.toString())
                }
            }
        }
    )
}


