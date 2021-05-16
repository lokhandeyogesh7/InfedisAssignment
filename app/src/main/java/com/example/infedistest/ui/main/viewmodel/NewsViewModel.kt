package com.example.infedistest.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infedistest.data.model.NewsResponse
import com.example.infedistest.data.repository.NewsRepository
import com.example.infedistest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val news = MutableLiveData<Resource<NewsResponse>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        news.postValue(Resource.loading(null))
        compositeDisposable.add(
            newsRepository.fetchNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newsList ->
                        news.postValue(Resource.success(newsList))
                    }, {
                        news.postValue(Resource.failed(null, "Something went wrong"))
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getNews(): LiveData<Resource<NewsResponse>> {
        return news
    }
}