package com.example.bookshelfgooglebookapi

import android.app.Application
import com.example.bookshelfgooglebookapi.data.AppContainer
import com.example.bookshelfgooglebookapi.data.DefaultAppContainer

class BookApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}