package com.example.make_best_music.ui.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onGoogleLoginClicked() {
        // Handle Google Login
    }

    fun onFacebookLoginClicked() {
        // Handle Facebook Login
    }

    fun onXLoginClicked() {
        // Handle X Login
    }

    fun onCloseClicked() {
        // Handle Close
    }
}

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
)
