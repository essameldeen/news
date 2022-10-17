package com.example.news_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utils.Resource
import com.example.news_domain.usacase.GetNewsArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val useCase: GetNewsArticleUseCase) : ViewModel() {

    private val _newsArticles = MutableStateFlow(NewsState())

    val newsArticles: StateFlow<NewsState> = _newsArticles

    init {
        getNewsArticle()
    }

    fun getNewsArticle() {
        useCase.invoke().onEach {

            when (it) {
                is Resource.Loading -> {
                    _newsArticles.value = NewsState(isLoading = true)
                }
                is Resource.Error -> {
                    _newsArticles.value = NewsState(error = it.errorMessage)
                }
                is Resource.Success -> {
                    _newsArticles.value = NewsState(data = it.data)

                }
            }
        }.launchIn(viewModelScope)
    }
}