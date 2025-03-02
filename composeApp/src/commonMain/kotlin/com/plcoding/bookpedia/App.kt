package com.barissemerci.bookpedia

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.barissemerci.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.barissemerci.bookpedia.book.data.repository.DefaultBookRepository
import com.barissemerci.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.barissemerci.bookpedia.book.presentation.book_list.BookListViewModel
import com.plcoding.bookpedia.core.data.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    engine: HttpClientEngine
) {

    BookListScreenRoot(
        viewModel = remember {
            BookListViewModel(
                bookRepository = DefaultBookRepository(
                    remoteBookDataSource = KtorRemoteBookDataSource(
                        httpClient = HttpClientFactory
                            .create(engine = engine) // Different engine for different platforms
                        // Android uses OkHttp, iOS uses Darwin, Desktop uses OkHttp
                    )
                )
            )
        },
        onBookClick = { book -> }
    )
}