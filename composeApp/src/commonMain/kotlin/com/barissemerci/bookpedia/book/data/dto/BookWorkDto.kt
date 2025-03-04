package com.barissemerci.bookpedia.book.data.dto

import BookWorkDtoSerializer
import kotlinx.serialization.Serializable

@Serializable(
with = BookWorkDtoSerializer::class
)
data class BookWorkDto(
    val description : String? = null
)
