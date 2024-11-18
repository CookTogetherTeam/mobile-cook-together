package com.cooktogether.data

import com.cooktogether.data.network.RecipesService
import com.cooktogether.data.network.RecipesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://cooktogether.duckdns.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesCharactersService(retrofit: Retrofit): RecipesApi {
        return retrofit.create(RecipesApi::class.java)
    }

    @Provides
    fun provideRecipesService(api: RecipesApi): RecipesService {
        return RecipesServiceImpl(api)
    }
}