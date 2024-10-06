package com.example.bookshelfgooglebookapi.network

import com.example.bookshelfgooglebookapi.model.Book
import com.example.bookshelfgooglebookapi.model.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("volumes")
    suspend fun getSearchResult(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 50,
        @Query("startIndex") startIndex: Int = 0
    ): Response<BooksResponse>
}
