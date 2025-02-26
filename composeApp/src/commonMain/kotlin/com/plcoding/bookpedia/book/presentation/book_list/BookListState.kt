package com.plcoding.bookpedia.book.presentation.book_list

import com.barissemerci.bookpedia.core.presentation.UiText
import com.plcoding.bookpedia.book.domain.Book

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks : List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val selectedTabIndex : Int = 0,
    val errorMessage : UiText? = null
)
 val books = (1..10).map{
    Book(
        title = "Book $it",
        authors = listOf("Author", "Author"),
        imageUrl = "www.test.com",
        averageRating = 4.5,
        numEditions = 5,
        id = it.toString(),
        description = "Description",
        numPages = 200,
        ratingCount = 3,
        firstPublishYear = "2012",
        languages = listOf("English")

    )
}