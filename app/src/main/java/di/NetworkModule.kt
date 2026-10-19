package com.myproj.a8147350assignment2.di

import com.myproj.a8147350assignment2.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.myproj.a8147350assignment2.data.repository.AuthRepository
import com.myproj.a8147350assignment2.data.repository.AuthRepositoryContract

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://nit3213apinew.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        repository: AuthRepository
    ): AuthRepositoryContract {
        return repository
    }

}
