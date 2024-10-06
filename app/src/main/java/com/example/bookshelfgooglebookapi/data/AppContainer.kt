package com.example.bookshelfgooglebookapi.data

import com.example.bookshelfgooglebookapi.network.BookApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val bookRepository: BookRepository
}

class DefaultAppContainer: AppContainer {
    private val BASE_URL=
        "https://www.googleapis.com/books/v1/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    override val bookRepository: BookRepository by lazy {
        NetworkBookRepository(retrofitService)
    }
}