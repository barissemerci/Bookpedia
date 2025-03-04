package com.barissemerci.bookpedia.book.data.mappers

import com.barissemerci.bookpedia.book.data.dto.SearchedBookDto
import com.barissemerci.bookpedia.book.data.database.BookEntity
import com.barissemerci.bookpedia.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"), // remove the /works/ prefix
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${coverAlternativeKey}-L.jpg"
        },
        authors = authorNames ?: emptyList(),
        languages = languages ?: emptyList(),
        description = null,
        firstPublishYear = firstPublishYear?.toString(),
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numPagesMedian,
        numEditions = numEditions ?: 0,
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        imageUrl = imageUrl,
        authors = authors,
        languages = languages,
        description = description,
        firstPublishYear = firstPublishYear,
        ratingsAverage = averageRating,
        ratingsCount = ratingCount,
        numberPagesMedian = numPages,
        numEditions = numEditions
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        imageUrl = imageUrl,
        authors = authors,
        languages = languages,
        description = description,
        firstPublishYear = firstPublishYear,
        averageRating = ratingsAverage,
        ratingCount = ratingsCount,
        numPages = numberPagesMedian,
        numEditions = numEditions
    )

}