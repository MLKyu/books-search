package com.alansoft.kapaycote.data

import com.alansoft.kapaycote.data.response.BooksSearchResponse
import java.util.*
import javax.inject.Inject

class SearchCacheDataSource
@Inject constructor() {

    private val cached: LinkedList<Data> = LinkedList()

    //    override
    fun pushQueryResponse(query: String, page: Int, queryResponse: BooksSearchResponse) {
        if (this.cached.size >= 5) {
            this.cached.removeFirst()
        }
        cached.addLast(Data(query, page, queryResponse))
    }

    //    override
    fun getQueryResponse(query: String, page: Int): BooksSearchResponse {
        return cached.first { it.query == query && it.page == page }.queryResponse
    }

    //    override
    fun isExistAndFresh(query: String, page: Int): Boolean {
        val index = isExist(query, page)
        if (index == -1) {
            return false
        }
        return isFresh(index)
    }

    private fun isExist(query: String, page: Int): Int {
        return cached.indexOfFirst { it.query == query && it.page == page }
    }

    private fun isFresh(index: Int): Boolean {
        if (cached[index].isFresh()) {
            return true
        }
        this.cached.removeAt(index)
        return false
    }


    private inner class Data(
        val query: String,
        val page: Int,
        val queryResponse: BooksSearchResponse
    ) {
        val createdAt: Long = System.currentTimeMillis()

        fun isFresh(): Boolean {
            val current = System.currentTimeMillis()
            return (current - this.createdAt) < timeout
        }
    }


    companion object {
        private const val timeout: Long = 10 * 60 * 1000 // 10분
    }

}