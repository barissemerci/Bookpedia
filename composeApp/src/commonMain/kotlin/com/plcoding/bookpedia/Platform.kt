package com.barissemerci.bookpedia

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform