@file:OptIn(FlowPreview::class)

package com.barissemerci.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barissemerci.bookpedia.book.domain.Book
import com.barissemerci.bookpedia.core.domain.onError
import com.barissemerci.bookpedia.core.domain.onSuccess
import com.barissemerci.bookpedia.book.domain.BookRepository
import com.barissemerci.bookpedia.core.presentation.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cachedBooks.isEmpty()) { // if there are no cached books, fetch them from the repository once
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )

    private var cachedBooks = emptyList<Book>()
    private var searchJob: Job? = null

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is BookListAction.OnBookClick -> {
                // Handle book click
            }

            is BookListAction.OnTabSelected -> {
                println(
                    "Selected tab index: ${action.index}"
                )
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map {
                it.searchQuery // get string emitted by state flow
            }
            .distinctUntilChanged() // only emit when the value changes
            .debounce(500L) // trigger search after 500ms of no typing
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResults = cachedBooks
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel() // cancel previous search job if it exists when a new search is triggered while user typing new query
                        searchJob = searchBooks(query)
                    }
                }

            }.launchIn(viewModelScope)


    }

    private fun searchBooks(query: String) =
        viewModelScope.launch { // if a new search is triggered, cancel the previous one
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }
            bookRepository.searchBooks(query)
                .onSuccess { searchResults ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            searchResults = searchResults
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            searchResults = emptyList(),
                            isLoading = false,
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
}