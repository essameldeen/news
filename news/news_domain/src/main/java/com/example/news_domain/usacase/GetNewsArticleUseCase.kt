package com.example.news_domain.usacase

import com.example.common_utils.Resource
import com.example.news_domain.model.Article
import com.example.news_domain.repo.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetNewsArticleUseCase(private val newsRepo: NewsRepository) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = newsRepo.getNewsArticle()))
        } catch (e: Exception) {
            emit(Resource.Error(errorMessage = e.message.toString()))
        }
    }


}