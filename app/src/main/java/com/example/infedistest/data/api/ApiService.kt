package com.example.infedistest.data.api

import com.example.infedistest.data.model.NewsResponse
import com.example.infedistest.data.model.booksresponse.BooksResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(NEWS_API)
    fun fetchNews(
        @Query(COUNTRY_QUERY) country: String,
        @Query(APIKEY_QUERY) apiKey: String
    ): Single<NewsResponse>

    @GET(BOOKS_API)
    fun fetchBooks(@Query(BOOKS_QUERY) queryString: String): Single<BooksResponse>

    companion object {
        const val BOOKS_API = "books/v1/volumes"
        const val BOOKS_QUERY = "q"
        const val NEWS_API = "v2/top-headlines"
        const val COUNTRY_QUERY = "country"
        const val APIKEY_QUERY = "apiKey"
    }
}