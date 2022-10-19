package com.example.news.di

import android.content.Context
import com.example.common_utils.Navigator
import com.example.news.database.ApplicationDataBase
import com.example.news.navigation.DefaultNavigator
import com.example.news_data.room.NewsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideProvider(): Navigator.Provider {
        return DefaultNavigator()
    }

    @Provides
    @Singleton
    fun provideNewsDataBase(@ApplicationContext context: Context): ApplicationDataBase {
        return ApplicationDataBase.getInstance(context)
    }

    @Provides
    fun provideNewsDAO(applicationDataBase: ApplicationDataBase): NewsDAO {
        return applicationDataBase.getNewsDAO()
    }
}