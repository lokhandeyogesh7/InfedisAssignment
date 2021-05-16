package com.example.infedistest.data.api

import com.example.infedistest.data.model.NewsResponse
import com.example.infedistest.data.model.booksresponse.BooksResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    fun fetchNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Single<NewsResponse>

    @GET("books/v1/volumes")
    fun fetchBooks(@Query("q") queryString: String): Single<BooksResponse>
}