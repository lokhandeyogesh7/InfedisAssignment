package com.example.infedistest.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.infedistest.data.repository.BaseRepository
import com.example.infedistest.data.repository.BooksRepository
import com.example.infedistest.data.repository.NewsRepository
import com.example.infedistest.ui.main.viewmodel.BooksViewModel
import com.example.infedistest.ui.main.viewmodel.NewsViewModel

class ViewModelFactory(val repository: BaseRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)){
            return NewsViewModel(repository as NewsRepository) as T
        }else if(modelClass.isAssignableFrom(BooksViewModel::class.java)){
            return BooksViewModel(repository as BooksRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}