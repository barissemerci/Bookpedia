package com.barissemerci.bookpedia

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.barissemerci.bookpedia.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ) {
            App()
        }
    }
}