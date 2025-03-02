package com.barissemerci.bookpedia.book.data.repository

import com.barissemerci.bookpedia.book.data.mappers.toBook
import com.barissemerci.bookpedia.core.domain.Result
import com.barissemerci.bookpedia.book.data.network.RemoteBookDataSource
import com.barissemerci.bookpedia.book.domain.Book
import com.barissemerci.bookpedia.core.domain.DataError
import com.barissemerci.bookpedia.core.domain.map
import com.plcoding.bookpedia.book.domain.BookRepository

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query).map { dto ->
            dto.results.map { it.toBook() }
        }
    }
}