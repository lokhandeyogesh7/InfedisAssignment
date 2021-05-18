package com.example.infedistest.data.api

object RetrofitWrapper {

    private var NEWS_URL = "https://newsapi.org/"
    private var BOOKS_URL = "https://www.googleapis.com/"
    val booksClient by lazy {
        RetrofitClient(BOOKS_URL).getApiServices()
    }
    val newsClient by lazy {
        RetrofitClient(NEWS_URL).getApiServices()
    }

}