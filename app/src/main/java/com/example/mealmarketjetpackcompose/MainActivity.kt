package com.example.mealmarketjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mealmarketjetpackcompose.data.navigation.BottomNavItem
import com.example.mealmarketjetpackcompose.data.navigation.MealBottomNav
import com.example.mealmarketjetpackcompose.data.navigation.NavGraph
import com.example.mealmarketjetpackcompose.ui.theme.MealMarketJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealMarketJetpackComposeTheme {
                val navController = rememberNavController()
                var selectedItem by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

                Scaffold(
                    bottomBar = {
                                MealBottomNav(
                                    selectedItem = selectedItem,
                                    onItemSelected = {item ->
                                                     selectedItem = item

                                    },
                                    navController = navController
                                )
                    },
                    content = {paddingValues ->
                        NavGraph(navHostController =navController,modifier = Modifier.padding(paddingValues)  )
                    }
                )
            }
        }
    }
}