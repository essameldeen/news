package com.example.search_data.repository

import com.example.search_data.mapper.toDomainArticle
import com.example.search_data.network.SearchApi
import com.example.search_domain.model.Article
import com.example.search_domain.repository.SearchRepository

class SearchRepoImp(private val searchApi: SearchApi) : SearchRepository {
    override suspend fun getSearchArticles(map: MutableMap<String, String>): List<Article> {
        return searchApi.getSearchArticles(map).articles.map { it.toDomainArticle() }
    }
}