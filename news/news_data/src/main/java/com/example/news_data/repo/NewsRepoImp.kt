package com.example.news_data.repo

import com.example.news_data.mapper.toDomainArticle
import com.example.news_data.network.NewsApiService
import com.example.news_data.room.NewsDAO
import com.example.news_domain.model.Article
import com.example.news_domain.repo.NewsRepository

class NewsRepoImp(private val newsApiService: NewsApiService, private val newsDAO: NewsDAO) :
    NewsRepository {
    override suspend fun getNewsArticle(): List<Article> {
        return try {
            val result = newsApiService.getNewsArticle("us").articles.map { it.toDomainArticle() }
            newsDAO.insertList(result)
            newsDAO.getNewsArticles()
        } catch (e: Exception) {
            newsDAO.getNewsArticles()
        }
    }
}