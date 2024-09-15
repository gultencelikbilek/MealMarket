package com.example.mealmarketjetpackcompose.feature.home_list_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealmarketjetpackcompose.data.NetworkResult
import com.example.mealmarketjetpackcompose.data.di.usecase.MealListUseCase
import com.example.mealmarketjetpackcompose.domain.model.Yemekler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealListUseCase: MealListUseCase
): ViewModel(){

    private val _mealResponse = MutableStateFlow<MealState?>(null)
    val mealResponse =  _mealResponse.asStateFlow()

    private var _isLoading = mutableStateOf(false)
    var isLoading = _isLoading


    fun mealList()= viewModelScope.launch {
        mealListUseCase.invoke().collect{result ->
            when(result){
                is NetworkResult.Error ->{
                    _mealResponse.value = MealState(
                        isError = result.message
                    )
                    Log.d("homeviewmodelError:",result.message)

                }
                NetworkResult.Loading -> {
                    _isLoading != _isLoading
                }
                is NetworkResult.Succes -> {
                    _mealResponse.value  = MealState(
                        data = result.data
                    )
                    Log.d("homeviewmodelSucces:",result.data.toString())
                }
            }
        }

    }
}

data class MealState(
    val data : List<Yemekler>? =null,
    val isError : String = ""
)