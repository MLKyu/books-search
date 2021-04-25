package com.alansoft.kapaycote.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.alansoft.kapaycote.data.Result
import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.repository.SearchRepository
import com.alansoft.kapaycote.utils.DEBOUNCE_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val query = MutableStateFlow("")
    val _results: MutableLiveData<Result<BooksSearchResponse>> =
        query
            // Discard text typed in a very short time to avoid many network calls
            .debounce(DEBOUNCE_MILLIS)
            // Filter empty text to avoid unnecessary network call
            .filter { text ->
                text.isNotEmpty()
            }
            // When a new text is set, transform it in Result<RedditImages> by triggering a
            // network call
            .flatMapLatest { text ->
                repository.getSearchBooks(text)
            }
            // Create a LiveData from Flow
            .asLiveData() as MutableLiveData
    val results: LiveData<Result<BooksSearchResponse>> = _results

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == query.value) {
            return
        }
//        nextPageHandler.reset()
        if (input.isNotBlank()) {
            query.value = input
        }
    }

    interface Delegate {
        fun querySubmitted(query: String)
        fun queryChanged(text: String)
    }
}