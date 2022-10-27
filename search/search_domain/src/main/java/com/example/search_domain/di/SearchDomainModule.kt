package com.example.search_domain.di

import com.example.search_domain.repository.SearchRepository
import com.example.search_domain.usecases.GetSearchArticles
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object SearchDomainModule {

    @Provides
    fun provideUseCaseSearch(searchRepository: SearchRepository): GetSearchArticles {
        return GetSearchArticles(searchRepository)
    }
}