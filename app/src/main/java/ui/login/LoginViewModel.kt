package com.myproj.a8147350assignment2.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproj.a8147350assignment2.data.repository.AuthRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()

    data class Success(
        val keypass: String
    ) : LoginState()

    data class Error(
        val message: String
    ) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepositoryContract
) : ViewModel() {

    private val _loginState =
        MutableStateFlow<LoginState>(LoginState.Idle)

    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {

        if (_loginState.value is LoginState.Loading) return

        viewModelScope.launch {

            _loginState.value = LoginState.Loading

            try {
                val keypass = repository.login(
                    username,
                    password
                )

                _loginState.value =
                    LoginState.Success(keypass)

            } catch (e: HttpException) {

                val message = when (e.code()) {
                    400, 401, 403 -> "Invalid student ID or first name"
                    else -> "Server error: ${e.code()}"
                }

                _loginState.value =
                    LoginState.Error(message)

            } catch (e: IOException) {

                _loginState.value =
                    LoginState.Error(
                        "Network error. Check your internet connection."
                    )

            } catch (e: Exception) {

                _loginState.value =
                    LoginState.Error(
                        "Something went wrong. Please try again."
                    )
            }
        }
    }
}
