package com.example.mealmarketjetpackcompose.data.di

import android.content.Context
import androidx.room.Room
import com.example.mealmarketjetpackcompose.data.Constants
import com.example.mealmarketjetpackcompose.data.db.FavoriteDao
import com.example.mealmarketjetpackcompose.data.db.FavoriteDatabase
import com.example.mealmarketjetpackcompose.data.di.repoimpl.MealRepositoryImpl
import com.example.mealmarketjetpackcompose.data.network.ApiService
import com.example.mealmarketjetpackcompose.domain.repo.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(@ApplicationContext context: Context): ApiService =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)


    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): FavoriteDatabase =
          Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "favorite_db"
        ).build()




    @Provides
    @Singleton
    fun providesRepoImpl(@ApplicationContext context: Context): MealRepository =
        MealRepositoryImpl(context)
}