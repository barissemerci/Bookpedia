package com.plcoding.bookpedia

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreen
import com.plcoding.bookpedia.book.presentation.book_list.BookListState
import com.plcoding.bookpedia.book.presentation.book_list.books
import com.plcoding.bookpedia.book.presentation.book_list.components.BookListItem
import com.plcoding.bookpedia.book.presentation.book_list.components.BookSearchBar

@Preview(showBackground = true)
@Composable
private fun BookSearchBarPreview() {

    BookSearchBar(
        searchQuery = "Search query",
        onSearchQueryChange = {},
        onImeSearch = {},
        modifier = Modifier.fillMaxWidth()
    )

}

@Preview(showBackground = true)
@Composable
private fun BookListItemPreview() {

    BookListItem(
        book = Book(
            title = "Title",
            authors = listOf("Author", "Author"),
            imageUrl = "https://images.unsplash.com/photo-1622838320000-4",
            averageRating = 4.5,
            numEditions = 5,
            id = "3",
            description = "Description",
            numPages = 200,
            ratingCount = 3,
            firstPublishYear = "2012",
            languages = listOf("English")

        ),
        onClick = {},
        modifier = Modifier.fillMaxWidth()
    )
}



@Preview(showBackground = true)
@Composable
private fun BookListScreenPreview(){
    BookListScreen(
        state = BookListState(
            searchResults = books,
        ),
        onAction = {}
    )
}