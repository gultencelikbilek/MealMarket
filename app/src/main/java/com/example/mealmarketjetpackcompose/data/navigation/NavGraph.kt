package com.example.mealmarketjetpackcompose.data.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mealmarketjetpackcompose.R
import com.example.mealmarketjetpackcompose.feature.cart_screen.CartScreen
import com.example.mealmarketjetpackcompose.feature.favorite_list_screen.FavoriteScreen
import com.example.mealmarketjetpackcompose.feature.home_list_screen.MealMarketScreen
import com.example.mealmarketjetpackcompose.ui.theme.MainColor


sealed class BottomNavItem(
    var title: String,
    var icon: Int
) {
    data object Home : BottomNavItem("Home", R.drawable.home_full)
    data object Favorite : BottomNavItem("Favorite", R.drawable.like)
    data object Cart : BottomNavItem("Cart", R.drawable.cart)
}
@Composable
fun MealBottomNav(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit,
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite,
        BottomNavItem.Cart
    )

    NavigationBar(
        containerColor = Color(0XFF4B57A1),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        items.forEach { item ->
            AddItem(
                screen = item,
                onNavigateToHome = {
                    onItemSelected(BottomNavItem.Home) // Seçili öğeyi güncelle
                    navController.navigate(Screen.MealMarketScreen.route)
                },
                onNavigateToFavorite = {
                    onItemSelected(BottomNavItem.Favorite)
                    navController.navigate(Screen.FavoriteScreen.route)
                },
                onNavigateToCart = {
                    onItemSelected(BottomNavItem.Cart)
                    navController.navigate(Screen.CartScreen.route)
                },
                selectedItem = selectedItem == item
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    onNavigateToHome: () -> Unit,
    onNavigateToFavorite: () -> Unit,
    onNavigateToCart: () -> Unit,
    selectedItem: Boolean
) {
    NavigationBarItem(
        selected = selectedItem,
        onClick = {
            if (!selectedItem) {
                when (screen) {
                    BottomNavItem.Cart -> onNavigateToCart.invoke()
                    BottomNavItem.Favorite -> onNavigateToFavorite.invoke()
                    BottomNavItem.Home -> onNavigateToHome.invoke()
                }
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        },
        label = {
            Text(
                text = screen.title,
                fontSize = 7.sp,
                modifier = Modifier.padding(top = 10.dp),
                style = TextStyle(color = Color.White)
            )
        },
        alwaysShowLabel = selectedItem,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            indicatorColor = MainColor
        ),
    )
}

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(navController = navHostController, startDestination = Screen.MealMarketScreen.route) {
        composable(Screen.MealMarketScreen.route) {
            MealMarketScreen(navHostController)
        }
        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navHostController)
        }
        composable(Screen.CartScreen.route) {
            CartScreen(navHostController)
        }
    }
}