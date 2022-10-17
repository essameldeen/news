package com.example.news_data.repo

import com.example.news_data.mapper.toDomainArticle
import com.example.news_data.network.NewsApiService
import com.example.news_domain.model.Article
import com.example.news_domain.repo.NewsRepository

class NewsRepoImp(private val newsApiService: NewsApiService) : NewsRepository {
    override suspend fun getNewsArticle(): List<Article> {
        return newsApiService.getNewsArticle("us").articles.map { it.toDomainArticle() }
    }
}