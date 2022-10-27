package com.example.search_domain.repository

import com.example.search_domain.model.Article

interface SearchRepository {

    suspend fun getSearchArticles(map: MutableMap<String,String>):List<Article>
}