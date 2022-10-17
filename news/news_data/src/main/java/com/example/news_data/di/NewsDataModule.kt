package com.example.news_data.di

import com.example.news_data.network.NewsApiService
import com.example.news_data.repo.NewsRepoImp
import com.example.news_domain.repo.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsDataModule {

    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsRepository(newsApiService: NewsApiService): NewsRepository {
        return NewsRepoImp(newsApiService)
    }
}