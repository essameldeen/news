package com.example.search_data.mapper

import com.example.search_data.model.ArticleDto
import com.example.search_domain.model.Article


fun ArticleDto.toDomainArticle(): Article {

    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        title = this.title ?: "",
        urlToImage = this.urlToImage ?: ""
    )
}