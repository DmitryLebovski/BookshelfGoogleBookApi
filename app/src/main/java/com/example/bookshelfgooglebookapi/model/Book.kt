package com.example.bookshelfgooglebookapi.model

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class BooksResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<Book>
)

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val categories: List<String>?,
    val imageLinks: ImageLinks?,
    val previewLink: String?
)

data class ImageLinks(
    @Json(name = "smallThumbnail") val smThPic: String,
    @Json(name = "thumbnail") val bigPic: String
)
