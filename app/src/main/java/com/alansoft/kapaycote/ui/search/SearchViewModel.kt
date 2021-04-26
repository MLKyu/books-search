package com.alansoft.kapaycote.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alansoft.kapaycote.data.Result
import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.repository.SearchRepository
import com.alansoft.kapaycote.utils.FIRST_PAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val query = MutableStateFlow(Pair("", FIRST_PAGE))
    val _results: MutableLiveData<Result<BooksSearchResponse>> =
        query
            // Discard text typed in a very short time to avoid many network calls
//            .debounce(DEBOUNCE_MILLIS)
            // Filter empty text to avoid unnecessary network call
            .filter {
                it.first.isNotEmpty()
            }
            // When a new text is set, transform it in Result<RedditImages> by triggering a
            // network call
            .flatMapLatest {
                repository.getSearchBooks(it.first, it.second)
            }
            // Create a LiveData from Flow
            .asLiveData() as MutableLiveData
    val results: LiveData<Result<BooksSearchResponse>> = _results

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == query.value.first) {
            return
        }
//        nextPageHandler.reset()
        if (input.isNotBlank()) {
            query.value = input to FIRST_PAGE
        }
    }

    fun loadNextPage() {
        (results.value as? Result.Success)?.run {
            if (data.meta?.isEnd == false) {
                query.value.let {
                    if (it.first.isNotBlank()) {
                        query.value = it.first to data.meta.page + 1
                    }
                }
            }
        }
    }

    interface Delegate {
        fun querySubmitted(query: String)
        fun queryChanged(text: String)
    }
}