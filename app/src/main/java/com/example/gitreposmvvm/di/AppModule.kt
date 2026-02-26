package com.example.gitreposmvvm.di

import com.example.gitreposmvvm.data.paging.GithubPagingSource
import com.example.gitreposmvvm.data.remote.ApiService
import com.example.gitreposmvvm.data.repository.GithubRepositoryImpl
import com.example.gitreposmvvm.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): GithubRepository =
        GithubRepositoryImpl(apiService)
}