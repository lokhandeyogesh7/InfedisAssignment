package com.example.infedistest.data.repository

import com.example.infedistest.data.model.NewsResponse
import io.reactivex.Single

class NewsRepository() : BaseRepository() {
    fun fetchNews(): Single<NewsResponse> {
        return apiService.fetchNews("in","874f6da3f60f41ac8b3aefdb28a2c881")
    }
}