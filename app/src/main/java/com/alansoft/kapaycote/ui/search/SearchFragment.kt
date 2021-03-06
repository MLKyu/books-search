package com.alansoft.kapaycote.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.Result
import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.SearchFragmentBinding
import com.alansoft.kapaycote.ui.base.BaseFragment
import com.alansoft.kapaycote.utils.getNavigationResult
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by LEE MIN KYU on 2021/04/20
 * Copyright © 2021 Dreamus Company. All rights reserved.
 */
@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchFragmentBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private val adapter: SearchListAdapter = SearchListAdapter(this::onItemClicked)

    override fun getLayoutId(): Int = R.layout.search_fragment

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_items, menu)
        setSearchMenu(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setRecyclerAdapter()
        setSubscribe()
    }

    private fun setSubscribe() {
        viewModel.results.observe(viewLifecycleOwner, {
            when (it) {
                is Result.Loading -> {
                    showProgressBar()
                }
                is Result.Success -> {
                    showRecyclerView()
                    setResultData(it.data)
                }
                is Result.Empty -> {
                    showErrorView("검색 결과가 없습니다.")
                }
                is Result.Error -> {
                    val message = if (it.isNetworkError) {
                        "네트워크 연결을 확인 하세요."
                    } else {
                        it.exception.message
                    }
                    showErrorView(message)
                }
                else -> {
                    // nothing
                }
            }
        })

        getNavigationResult<Document>(R.id.searchFragment).observe(viewLifecycleOwner) { result ->
            val notify = adapter.currentList[result.position].like != result.like
            if (notify) {
                adapter.currentList[result.position].setLike(result.like)
                adapter.notifyItemChanged(result.position)
            }
        }
    }


    private fun setRecyclerAdapter() {
        binding.recyclerView.run {
            setHasFixedSize(true)
            clearOnScrollListeners()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastPosition = layoutManager.findLastVisibleItemPosition()
                    if (lastPosition == (adapter?.itemCount ?: RecyclerView.NO_POSITION) - 1) {
                        viewModel.loadNextPage()
                    }
                }
            })
            adapter = this@SearchFragment.adapter
        }
    }

    private fun setResultData(data: BooksSearchResponse) {
        val list: MutableList<Document?> = ArrayList()
        if (data.meta?.page ?: -1 > 1) {
            list.addAll(adapter.currentList)
        }
        data.documents?.let {
            list.addAll(it)
        }
        adapter.submitList(list)
    }

    private fun showRecyclerView() {
        with(binding) {
            recyclerView.visibility = View.VISIBLE
            errorTv.visibility = View.GONE
            hideProgressBar()
        }
    }

    private fun showErrorView(message: String?) {
        with(binding) {
            recyclerView.visibility = View.INVISIBLE
            errorTv.visibility = View.VISIBLE
            errorTv.text = message
            hideProgressBar()
        }
        adapter.submitList(emptyList())
    }

    private fun setSearchMenu(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).run {
            queryHint = "책 이름을 입력하세요"
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.setQuery(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }
            })
        }
    }

    private fun onItemClicked(item: Pair<Document, Int>) {
        val direction =
            SearchFragmentDirections.actionListFragmentToDetailFragment(item.first.apply {
                setPostion(item.second)
            })
        findNavController().navigate(direction)
    }


    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

}