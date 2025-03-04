package com.barissemerci.bookpedia.book.data.network

import com.barissemerci.bookpedia.book.data.dto.BookWorkDto
import com.barissemerci.bookpedia.core.domain.Result
import com.barissemerci.bookpedia.book.data.dto.SearchResponseDto
import com.barissemerci.bookpedia.core.domain.DataError

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(
        bookWorkId: String
    ): Result<BookWorkDto, DataError.Remote>
}