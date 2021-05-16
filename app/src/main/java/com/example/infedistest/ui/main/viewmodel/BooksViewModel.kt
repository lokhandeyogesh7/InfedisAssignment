package com.example.infedistest.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.infedistest.data.model.booksresponse.BooksResponse
import com.example.infedistest.data.repository.BooksRepository
import com.example.infedistest.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BooksViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    val books = MutableLiveData<Resource<BooksResponse>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetchBooks(queryString: String) {
        books.postValue(Resource.loading(null))
        compositeDisposable.add(
            booksRepository.fetchBooks(queryString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { newsList ->
                        books.postValue(Resource.success(newsList))
                    }, {
                        books.postValue(Resource.failed(null, "Something went wrong"))
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}