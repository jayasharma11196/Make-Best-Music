package com.example.make_best_music.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LibraryViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()
}

data class LibraryUiState(
    val hasMusic: Boolean = false,
    val hasFolder: Boolean = false,
    val hasCover: Boolean = false
)
