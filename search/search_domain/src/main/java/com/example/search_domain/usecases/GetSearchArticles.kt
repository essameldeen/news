package com.example.search_domain.usecases

import com.example.common_utils.Resource
import com.example.search_domain.model.Article
import com.example.search_domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetSearchArticles(private val searchRepository: SearchRepository) {

    operator fun invoke(map: MutableMap<String, String>): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(searchRepository.getSearchArticles(map)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}