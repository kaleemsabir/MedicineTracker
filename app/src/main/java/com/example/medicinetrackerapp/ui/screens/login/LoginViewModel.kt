package com.example.medicinetrackerapp.ui.screens.login


import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinetrackerapp.data.AuthRepository
import com.example.medicinetrackerapp.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            val result = authRepository.login()
            _loginState.value = if (result.isSuccess) {
                LoginState.Success(result.getOrNull()!!)
            } else {
                LoginState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val userData: UserData) : LoginState()
    data class Error(val error: String) : LoginState()
}