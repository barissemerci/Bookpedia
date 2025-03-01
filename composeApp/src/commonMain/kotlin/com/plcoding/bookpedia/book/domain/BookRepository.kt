package com.plcoding.bookpedia.book.domain

import com.barissemerci.bookpedia.book.domain.Book
import com.barissemerci.bookpedia.core.domain.DataError
import com.barissemerci.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}