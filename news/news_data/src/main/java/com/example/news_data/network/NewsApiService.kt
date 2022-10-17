package com.example.news_data.network


import com.example.common_utils.Constant
import com.example.news_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {


    @GET("top-headlines")
    suspend fun getNewsArticle(
        @Query("country") country: String,
        @Query("category") category: String = Constant.CATEGORY,
        @Query("apiKey") apiKey: String = Constant.API_KEY
    ): NewsResponse

}