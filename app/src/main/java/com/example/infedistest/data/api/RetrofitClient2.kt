package com.example.infedistest.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient2 {

    companion object {
        private var retrofit2: Retrofit? = null
        private var  BASE_URL_2 = "https://www.googleapis.com/"

        fun getHttpClient2(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            return httpClient.build()
        }

        fun getRetrofitInstance2(): Retrofit {
            if (retrofit2 == null) {
                retrofit2 = Retrofit.Builder()
                    .baseUrl(BASE_URL_2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getHttpClient2())
                    .build()
            }
            return retrofit2!!
        }

        fun getApiServices2(): ApiService {
            return getRetrofitInstance2().create(ApiService::class.java)
        }

    }
}