package com.barissemerci.bookpedia.book.presentation.book_list

import com.barissemerci.bookpedia.core.presentation.UiText
import com.barissemerci.bookpedia.book.domain.Book

data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks : List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex : Int = 0,
    val errorMessage : UiText? = null
)
