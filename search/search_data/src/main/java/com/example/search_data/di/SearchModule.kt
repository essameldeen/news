package com.example.search_data.di

import com.example.search_data.network.SearchApi
import com.example.search_data.repository.SearchRepoImp
import com.example.search_domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object SearchModule {

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    fun provideSearchRepo(api: SearchApi): SearchRepository {
        return SearchRepoImp(api)
    }
}