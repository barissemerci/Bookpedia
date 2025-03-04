package com.barissemerci.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.barissemerci.bookpedia.book.data.database.DatabaseFactory
import com.barissemerci.bookpedia.book.data.database.FavoriteBookDatabase
import com.barissemerci.bookpedia.book.data.network.KtorRemoteBookDataSource
import com.barissemerci.bookpedia.book.data.network.RemoteBookDataSource
import com.barissemerci.bookpedia.book.data.repository.DefaultBookRepository
import com.barissemerci.bookpedia.book.presentation.book_list.BookListViewModel
import com.barissemerci.bookpedia.book.domain.BookRepository
import com.barissemerci.bookpedia.book.presentation.SelectedBookViewModel
import com.barissemerci.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.barissemerci.bookpedia.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module
val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }
    singleOf(
        ::KtorRemoteBookDataSource
    ).bind<RemoteBookDataSource>()

    singleOf(
        ::DefaultBookRepository
    ).bind<BookRepository>()

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single { get<FavoriteBookDatabase>().favoriteBookDao }


}