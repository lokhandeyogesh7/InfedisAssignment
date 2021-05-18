package com.example.infedistest.data.repository

import com.example.infedistest.data.api.RetrofitClient
import com.example.infedistest.data.api.RetrofitClient2
import com.example.infedistest.data.api.RetrofitWrapper

open class BaseRepository {
    val apiService = RetrofitWrapper.newsClient
    val apiService2 = RetrofitWrapper.booksClient
}