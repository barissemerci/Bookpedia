package com.barissemerci.bookpedia.book.presentation.book_detail

import com.barissemerci.bookpedia.book.domain.Book

class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book : Book? = null
)
