package com.example.bookshelfgooglebookapi.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfgooglebookapi.BookApplication
import com.example.bookshelfgooglebookapi.data.BookRepository
import com.example.bookshelfgooglebookapi.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookUiState {
    data class Success(val books: List<Book>): BookUiState
    object Error: BookUiState
    object Loading: BookUiState
}

class BookViewModel (private val bookRepository: BookRepository): ViewModel() {
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    init {
        getBooks("jazz history")
    }

    fun getBooks(query: String) {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                BookUiState.Success(bookRepository.getBooks(query))
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookApplication)
                val bookRepository = application.container.bookRepository
                BookViewModel(bookRepository)
            }
        }

    }
}