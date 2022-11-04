package com.example.search_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_utils.Resource
import com.example.search_domain.usecases.GetSearchArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val getSearchArticles: GetSearchArticles
) : ViewModel() {

    private val _searchResult = MutableStateFlow(SearchState())
    val searchResult: StateFlow<SearchState> = _searchResult


    fun searchForArticles(map: MutableMap<String, String>) {
        getSearchArticles(map).onEach {
            when (it) {
                is Resource.Loading -> _searchResult.value = SearchState(isLoading = true)
                is Resource.Error -> _searchResult.value = SearchState(error = it.errorMessage)
                is Resource.Success -> _searchResult.value = SearchState(data = it.data )
            }

        }.launchIn(viewModelScope)
    }

}