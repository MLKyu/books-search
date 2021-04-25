package com.alansoft.kapaycote.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.alansoft.kapaycote.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val query by lazy { MutableLiveData<String>() }
    val _results by lazy { query.switchMap { search -> repository.getSearchBooks(search) } as MutableLiveData }
//    val results: LiveData<Resource<SearchMerge>> by lazy { _results }

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