package com.example.infedistest.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infedistest.R
import com.example.infedistest.data.repository.BooksRepository
import com.example.infedistest.ui.base.ViewModelFactory
import com.example.infedistest.ui.main.adapter.BooksAdapter
import com.example.infedistest.ui.main.viewmodel.BooksViewModel
import com.example.infedistest.utils.Status
import com.example.infedistest.utils.visible

class BookFragment : Fragment() {
    lateinit var booksViewModel: BooksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        booksViewModel = ViewModelProvider(
            this,
            ViewModelFactory(BooksRepository())
        ).get(BooksViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.books_list)
        progressBar = view.findViewById(R.id.progressbar)
        val searchView = view.findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchView.clearFocus()
                    fetchBooks(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun fetchBooks(it: String) {
        booksViewModel.fetchBooks(it)
        booksViewModel.books.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visible(false)
                    recyclerView.visible(true)
                    it.data?.items?.let { response ->
                        recyclerView.layoutManager =
                            LinearLayoutManager(requireContext())
                        val dividerItemDecoration = DividerItemDecoration(
                            recyclerView.context,
                            (recyclerView.layoutManager as LinearLayoutManager).orientation
                        )
                        recyclerView.addItemDecoration(dividerItemDecoration)
                        val adapter = BooksAdapter(requireContext(), response)
                        recyclerView.adapter = adapter
                    }
                }
                Status.LOADING -> {
                    progressBar.visible(true)
                    recyclerView.visible(false)
                }
                Status.FAILED -> {
                    progressBar.visible(false)
                    recyclerView.visible(false)
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}