package com.example.mealmarketjetpackcompose.feature.home_list_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mealmarketjetpackcompose.feature.component.CartIconWithBadge
import com.example.mealmarketjetpackcompose.feature.component.HomeMealMarketCardComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealMarketScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeState = homeViewModel.mealResponse.collectAsState()
    val cartItemCountState = homeViewModel.itemCount.collectAsState()




    LaunchedEffect(homeState) {
        homeViewModel.mealList()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                actions = {
                    CartIconWithBadge(cartItemCount = cartItemCountState.value)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0XFF4B57A1)
                )
            )
        },
        content = { padding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(padding)

            ) {
                homeState.value?.data?.let { mealList ->
                    items(mealList) {
                        HomeMealMarketCardComponent(it,homeViewModel)

                    }
                }
            }
        }
    )
}
