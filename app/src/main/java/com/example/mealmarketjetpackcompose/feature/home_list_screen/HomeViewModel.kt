package com.example.mealmarketjetpackcompose.feature.home_list_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Operation
import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.data.di.usecase.DeleteFavoriteUseCase
import com.example.mealmarketjetpackcompose.data.di.usecase.GetAllFavoriteUseCase
import com.example.mealmarketjetpackcompose.data.di.usecase.GetByNameFavoriteUseCase
import com.example.mealmarketjetpackcompose.data.di.usecase.InsertFavoriteUseCase
import com.example.mealmarketjetpackcompose.data.di.usecase.MealListUseCase
import com.example.mealmarketjetpackcompose.domain.model.Meal
import com.example.mealmarketjetpackcompose.domain.model.Yemekler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealListUseCase: MealListUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getAllFavoriteUseCase: GetAllFavoriteUseCase,
    private val getByNameFavoriteUseCase: GetByNameFavoriteUseCase
) : ViewModel() {

    private val _mealResponse = MutableStateFlow<MealState?>(null)
    val mealResponse: StateFlow<MealState?> = _mealResponse.asStateFlow()

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var _itemCount = MutableStateFlow(0)
    val itemCount: StateFlow<Int> = _itemCount

    private val _favoriteMeals = MutableStateFlow<Map<String, Boolean>>(emptyMap()) // Yemek id'lerine göre favori durumlarını tutar
    val favoriteMeals: StateFlow<Map<String, Boolean>> = _favoriteMeals.asStateFlow()


    fun cartItemCount() = viewModelScope.launch {
        _itemCount.value += 1
    }

    /**
     * Yemek listesini alır ve günceller.
     */
    fun mealList() = viewModelScope.launch {
        mealListUseCase.invoke().collect { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _mealResponse.value = MealState(
                        isError = result.message
                    )
                    Log.d("homeviewmodelError:", result.message)
                }
                NetworkResult.Loading -> {
                    _isLoading.value = true
                }
                is NetworkResult.Succes -> {
                    _mealResponse.value = MealState(
                        data = result.data
                    )
                    _isLoading.value = false
                    Log.d("homeviewmodelSucces:", result.data.toString())
                }
            }
        }
    }

    fun getAllMeal() = viewModelScope.launch {
        val favorites = getAllFavoriteUseCase.invoke()
        _favoriteMeals.value = favorites.associate { it.yemek_id to true }
    }

    fun updateFavoriteMealList(yemekler: Yemekler) = viewModelScope.launch {
        val result = getByNameFavoriteUseCase.invoke(yemekler.yemek_adi)
        if (result == null){
            insertFavoriteUseCase.invoke(yemekler)
        }else{
            deleteFavoriteUseCase.invoke(yemekler)
        }
        getAllMeal()
    }
    // Bir yemeğin favori olup olmadığını kontrol et
    fun isFavorite(mealId : Int) : Boolean {
       return _favoriteMeals.value[mealId.toString()] ?: false
    }
}



data class MealState(
    val data : List<Yemekler>? =null,
    val isError : String = ""
)

data class FavoriteState(
    val dataFav : Meal? = null,
    val isError : String =""
)