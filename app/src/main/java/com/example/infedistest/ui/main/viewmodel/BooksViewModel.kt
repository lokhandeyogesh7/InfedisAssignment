package com.example.infedistest.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infedistest.data.model.booksresponse.BooksResponse
import com.example.infedistest.data.repository.BooksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BooksViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    val books = MutableLiveData<BooksResponse>()
    private val compositeDisposable = CompositeDisposable()
    var progressBar = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun fetchBooks(queryString: String) {
        progressBar.postValue(true)
        compositeDisposable.add(
            booksRepository.fetchBooks(queryString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newsList ->
                        books.postValue(newsList)
                        progressBar.postValue(false)
                    }, {
                        error.postValue(it.localizedMessage)
                        progressBar.postValue(false)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}