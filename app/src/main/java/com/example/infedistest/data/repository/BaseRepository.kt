package com.example.infedistest.data.repository

import com.example.infedistest.data.api.RetrofitClient
import com.example.infedistest.data.api.RetrofitClient2

open class BaseRepository {
    val apiService = RetrofitClient.getApiServices()
    val apiService2 = RetrofitClient2.getApiServices2()
}