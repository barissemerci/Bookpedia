package com.barissemerci.bookpedia.book.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Upsert
    suspend fun upsert(book: BookEntity)

    @Query("SELECT * FROM BookEntity")
    fun getFavoriteBooks(): Flow<List<BookEntity>> // Flow is a type that represents a stream of values that can be observed asynchronously
    //if you return flow type, you don't need to use suspend keyword

    @Query("SELECT * FROM BookEntity WHERE id = :bookId")
    suspend fun getFavoriteBookById(bookId: String): BookEntity?

    @Query("DELETE FROM BookEntity WHERE id = :bookId")
    suspend fun deleteFavoriteBookById(bookId: String)

}