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
import com.example.infedistest.data.model.booksresponse.Item
import com.example.infedistest.data.repository.BooksRepository
import com.example.infedistest.ui.base.ViewModelFactory
import com.example.infedistest.ui.main.adapter.BooksAdapter
import com.example.infedistest.ui.main.viewmodel.BooksViewModel
import com.example.infedistest.utils.Status
import com.example.infedistest.utils.visible

class BookFragment : Fragment() {
    private lateinit var adapter: BooksAdapter
    private var booksList = mutableListOf<Item>()
    lateinit var booksViewModel: BooksViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

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
                    booksViewModel.fetchBooks(it)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        createAdapter()
        initViewModel()
        showProgress()
        setAdapter()
        showError()
    }

    private fun createAdapter() {
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        adapter = BooksAdapter(requireContext(), booksList)
        recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        booksViewModel = ViewModelProvider(
            this,
            ViewModelFactory(BooksRepository())
        ).get(BooksViewModel::class.java)
    }

    private fun showProgress() {
        booksViewModel.progressBar.observe(viewLifecycleOwner, {
            progressBar.visible(it)
        })
    }


    private fun setAdapter() {
        booksViewModel.books.observe(viewLifecycleOwner, {
            booksList.clear()
            booksList.addAll(it.items as MutableList<Item>)
            adapter.notifyDataSetChanged()
        })
    }

    fun showError() {
        booksViewModel.error.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

}