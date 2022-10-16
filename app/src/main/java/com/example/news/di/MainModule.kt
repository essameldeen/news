package com.example.news.di

import com.example.common_utils.Navigator
import com.example.news.navigation.DefaultNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideProvider():Navigator.Provider{
        return  DefaultNavigator()
    }
}