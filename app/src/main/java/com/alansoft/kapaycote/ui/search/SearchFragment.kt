package com.alansoft.kapaycote.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alansoft.kapaycote.R
import com.alansoft.kapaycote.data.Result
import com.alansoft.kapaycote.data.response.BooksSearchResponse
import com.alansoft.kapaycote.data.response.Document
import com.alansoft.kapaycote.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModels()
    private val adapter: SearchListAdapter = SearchListAdapter(this::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_items, menu)
        setSearchView(menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setObserver()
        setRecyclerView()
    }

    private fun setObserver() {
        viewModel.results.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    showImagesRecyclerView(result.data)
                }
                is Result.Empty -> {
//                    val message = result
//                    showEmptyView(message
                }
                is Result.Error -> {
//                    val message = if (result.isNetworkError) {
//                        getString(R.string.no_internet)
//                    } else {
//                        getString(R.string.no_image_show)
//                    }
//                    showEmptyView(message)
                }
                else -> {
                    // nothing
                }
            }
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            adapter = this@SearchFragment.adapter
        }
    }

    private fun showImagesRecyclerView(data: BooksSearchResponse) {
        with(binding) {
            // Stop refreshing state
//            swipeRefreshLayout.isRefreshing = false
//            imagesRecyclerView.visibility = View.VISIBLE
//            noDataGroup.visibility = View.GONE
            progressBar.visibility = View.GONE
        }

        adapter.submitList(data.documents)
    }

    private fun showEmptyView(message: String) {
        with(binding) {
            // Stop refreshing state
//            swipeRefreshLayout.isRefreshing = false
//
//            imagesRecyclerView.visibility = View.INVISIBLE
//            noDataGroup.visibility = View.VISIBLE
//
//            noDataText.text = message

            progressBar.visibility = View.GONE
        }

        adapter.submitList(emptyList())
    }


    private fun setToolbar() {
//        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
    }

    private fun setSearchView(menu: Menu) {
        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).apply {
            queryHint = "책 이름을 입력하세요"
//                getString(R.string.subreddit)
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

    private fun onItemClicked(item: Document) {
        val direction = SearchFragmentDirections.actionListFragmentToDetailFragment(item)
        findNavController().navigate(direction)
    }
}