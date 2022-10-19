package com.example.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.news_data.room.NewsDAO
import com.example.news_domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ApplicationDataBase : RoomDatabase() {

    companion object {

        fun getInstance(context: Context): ApplicationDataBase =
            Room.databaseBuilder(context, ApplicationDataBase::class.java, "news_dp")
                .fallbackToDestructiveMigration()
                .build()
    }

    abstract fun getNewsDAO(): NewsDAO
}