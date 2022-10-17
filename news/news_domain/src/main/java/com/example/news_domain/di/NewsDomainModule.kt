package com.example.news_domain.di

import com.example.news_domain.repo.NewsRepository
import com.example.news_domain.usacase.GetNewsArticleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsDomainModule {

    @Singleton
    @Provides
    fun provideGetNewsArticleUseCase(newsRepository: NewsRepository): GetNewsArticleUseCase {
        return GetNewsArticleUseCase(newsRepository)
    }
}