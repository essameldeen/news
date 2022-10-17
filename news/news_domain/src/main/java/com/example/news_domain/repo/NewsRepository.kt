package com.example.news_domain.repo

import com.example.news_domain.model.Article

interface NewsRepository {
    suspend fun getNewsArticle(): List<Article>
}