package com.example.infedistest.ui.main.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.infedistest.R
import com.example.infedistest.data.repository.NewsRepository
import com.example.infedistest.ui.base.ViewModelFactory
import com.example.infedistest.ui.main.adapter.NewsAdapter
import com.example.infedistest.ui.main.viewmodel.NewsViewModel
import com.example.infedistest.utils.Status
import com.example.infedistest.utils.visible


class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsViewModel = ViewModelProvider(
            this,
            ViewModelFactory(NewsRepository())
        ).get(NewsViewModel::class.java)

        newsViewModel.getNews().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visible(false)
                    recyclerView.visible(true)
                    it.data?.articles?.let { response->
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        val dividerItemDecoration = DividerItemDecoration(
                            recyclerView.context,
                            (recyclerView.layoutManager as LinearLayoutManager).orientation
                        )
                        recyclerView.addItemDecoration(dividerItemDecoration)
                        val adapter = NewsAdapter(requireContext(), response)
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
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.news_list)
        progressBar = view.findViewById(R.id.progressbar)
    }
}