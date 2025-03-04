package com.barissemerci.bookpedia.book.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = false) val id: String ,
    val title: String,
    val description : String?,
    val imageUrl : String,
    val languages : List<String>, // need TypeConverter
    val authors : List<String>,
    val firstPublishYear : String?,
    val ratingsAverage : Double?,
    val ratingsCount : Int?,
    val numberPagesMedian : Int?,
    val numEditions : Int
)