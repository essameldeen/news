package com.example.news_data.mapper

import com.example.news_data.model.ArticleDto
import com.example.news_domain.model.Article


fun ArticleDto.toDomainArticle(): Article {

    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        title = this.title ?: "",
        urlToImage = this.urlToImage ?: ""
    )
}