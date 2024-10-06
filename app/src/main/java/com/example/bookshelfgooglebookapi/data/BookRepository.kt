package com.example.bookshelfgooglebookapi.data

import com.example.bookshelfgooglebookapi.model.Book
import com.example.bookshelfgooglebookapi.network.BookApiService
import java.io.IOException

interface BookRepository {
    suspend fun getBooks(query: String): List<Book>
}

class NetworkBookRepository(
    private val bookApiService: BookApiService,
): BookRepository {
    override suspend fun getBooks(query: String): List<Book> {
        val response = bookApiService.getSearchResult(query, maxResults = 30)
        if (response.isSuccessful) {
            return response.body()?.items ?: emptyList()
        } else {
            throw IOException("Error fetching books: ${response.message()}")
        }
    }
}
