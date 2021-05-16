package com.example.infedistest.data.model.booksresponse

data class BooksResponse(
    val items: List<Item>,
    val kind: String,
    val totalItems: Double
)