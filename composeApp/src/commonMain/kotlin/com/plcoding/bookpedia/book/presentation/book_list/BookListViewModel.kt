package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookListViewModel : ViewModel() {
    private val _state = MutableStateFlow(BookListState())
    val state = _state.asStateFlow()

    fun onAction(action: BookListAction){
        when(action){
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
}