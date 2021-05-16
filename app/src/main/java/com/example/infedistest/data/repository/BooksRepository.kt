package com.example.infedistest.data.repository

import com.example.infedistest.data.model.booksresponse.BooksResponse
import io.reactivex.Single

class BooksRepository : BaseRepository() {

    fun fetchBooks(queryString: String): Single<BooksResponse> {
        return apiService2.fetchBooks(queryString)
    }
}