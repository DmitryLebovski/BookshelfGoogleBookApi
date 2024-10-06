package com.example.bookshelfgooglebookapi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfgooglebookapi.R
import com.example.bookshelfgooglebookapi.model.Book
import com.example.bookshelfgooglebookapi.model.BooksResponse

@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {

    when(bookUiState) {
        is BookUiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize()
        )

        is BookUiState.Success -> SuccessScreen(
            books = bookUiState.books,
            modifier = modifier.fillMaxSize()
        )

        is BookUiState.Loading -> LoadingScreen()
    }
}

@Composable
fun SuccessScreen(
    books: List<Book>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Card (
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = modifier.padding(horizontal = 4.dp),
            contentPadding = contentPadding
        ) {
            items(items = books, key =  { book -> book.id }) {
                book ->
                BookCard(
                    book = book,
                    modifier = modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.5f)
                )
            }

        }
    }
}

@Composable
fun BookCard(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(color = 0xFF3b3b3b))
    ) {
        Text(
            text = book.volumeInfo.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )

        Text(
            text = book.volumeInfo.authors.toString().replace("[", "").replace("]", ""),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )

        BookPhoto(
            book = book
        )
    }
}

@Composable
fun BookPhoto(
    book: Book,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(book.volumeInfo.imageLinks?.smThPic?.replace("http://", "https://"))
            .crossfade(true)
            .build(),
        contentDescription = "BookPhoto",
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.ic_connection_error),
        error = painterResource(id = R.drawable.ic_broken_image),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "loading"
    )
}

@Composable
fun ErrorScreen(
    modifier: Modifier
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = "error"
        )

        Text(text = "Internet error", modifier = Modifier.padding(16.dp))
    }
}
