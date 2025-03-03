package com.barissemerci.bookpedia

import androidx.compose.ui.window.ComposeUIViewController
import com.barissemerci.bookpedia.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}