package com.barissemerci.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.barissemerci.bookpedia.book.data.database.FavoriteBookDao
import com.barissemerci.bookpedia.book.data.mappers.toBook
import com.barissemerci.bookpedia.book.data.mappers.toBookEntity
import com.barissemerci.bookpedia.core.domain.Result
import com.barissemerci.bookpedia.book.data.network.RemoteBookDataSource
import com.barissemerci.bookpedia.book.domain.Book
import com.barissemerci.bookpedia.core.domain.DataError
import com.barissemerci.bookpedia.core.domain.map
import com.barissemerci.bookpedia.book.domain.BookRepository
import com.barissemerci.bookpedia.core.domain.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query).map { dto ->
            dto.results.map { it.toBook() }
        }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError> {
        //OFFLINE FIRST
        // if we have description of this book in database, we don't need to call the network
        val localResult = favoriteBookDao.getFavoriteBookById(bookId)

        return if (localResult == null) {
            remoteBookDataSource.getBookDetails(bookId).map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao.getFavoriteBooks()
            .map { bookEntities -> bookEntities.map { it.toBook() } }
    }

    override fun isBookFavorite(bookId: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoriteBooks().map { bookEntities ->
                bookEntities.any { it.id == bookId }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)

        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(bookId: String) {
        favoriteBookDao.deleteFavoriteBookById(bookId)
    }
}