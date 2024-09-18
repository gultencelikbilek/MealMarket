package com.example.mealmarketjetpackcompose.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mealmarketjetpackcompose.R
import com.example.mealmarketjetpackcompose.domain.model.Meal
import com.example.mealmarketjetpackcompose.domain.model.Yemekler
import com.example.mealmarketjetpackcompose.feature.home_list_screen.HomeViewModel

@Composable
fun HomeMealMarketCardComponent(yemekler: Yemekler, homeViewModel: HomeViewModel) {

    val isLiked = homeViewModel.isFavorite(yemekler.yemek_id.toInt())

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFC8CEF7)
        ),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LikeButton(
                initialIsLiked = isLiked,
                onLikeClick = { updatedIsLiked ->
                    homeViewModel.updateFavoriteMealList(yemekler)
                }
            )
            MealImage(yemekler.yemek_resim_adi)
            Spacer(modifier = Modifier.height(12.dp))
            MealName(yemekler.yemek_adi)
            Spacer(modifier = Modifier.height(10.dp))
            MealPrice(yemekler.yemek_fiyat)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartIconWithBadge(cartItemCount: Int) {
    BadgedBox(
        badge = {
            if (cartItemCount > 0) {
                Badge {
                    Text(text = "$cartItemCount")
                }
            }
        }
    ) {

    }
    Icon(
        painter = painterResource(id = R.drawable.cart),
        contentDescription = "",
        modifier = Modifier.size(24.dp)
    )
}

@Composable
fun MealPrice(yemekFiyat: String) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = yemekFiyat,
            modifier = Modifier.padding(start = 10.dp, top = 16.dp),
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                color = Color.Gray
            )
        )
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
        }
    }
}

@Composable
fun MealName(yemekAdi: String) {
    Text(
        text = yemekAdi,
        style = TextStyle(
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            color = Color.DarkGray,
        )
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = "Ücretsiz gönderim",
        style = TextStyle(
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            color = Color.Gray,
        )
    )
}

@Composable
fun MealImage(yemekResimAdi: String) {
    val painter = rememberAsyncImagePainter(
        "http://kasimadalan.pe.hu/yemekler/resimler/${yemekResimAdi}",
    )
    Image(painter = painter, contentDescription = "")
}

@Composable
fun LikeButton(
    initialIsLiked: Boolean,
    onLikeClick: (Boolean) -> Unit
) {
    var isLiked by remember { mutableStateOf(initialIsLiked) }

    // `LaunchedEffect` ile `initialIsLiked` değiştiğinde `isLiked`'ı güncelle
    LaunchedEffect(initialIsLiked) {
        isLiked = initialIsLiked
    }
    val icon = if (isLiked) R.drawable.like_full else R.drawable.like


    IconButton(
        onClick = {
            onLikeClick.invoke(isLiked)
            isLiked = !isLiked
        },
        modifier = Modifier
            .size(30.dp)
            .padding(start = 20.dp, top = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Like Icon"
        )
    }
}


