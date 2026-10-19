package com.myproj.a8147350assignment2

import com.myproj.a8147350assignment2.data.repository.AuthRepositoryContract
import com.myproj.a8147350assignment2.ui.login.LoginState
import com.myproj.a8147350assignment2.ui.login.LoginViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val repository =
        mockk<AuthRepositoryContract>()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loginSuccess() = runTest(dispatcher) {
        coEvery {
            repository.login("8147350", "TestName")
        } returns "books"

        val viewModel = LoginViewModel(repository)

        viewModel.login("8147350", "TestName")
        advanceUntilIdle()

        val state = viewModel.loginState.value

        assertTrue(state is LoginState.Success)
        assertEquals(
            "books",
            (state as LoginState.Success).keypass
        )
    }

    @Test
    fun loginInvalidCredentials() = runTest(dispatcher) {
        coEvery {
            repository.login(any(), any())
        } throws retrofit2.HttpException(
            retrofit2.Response.error<String>(
                401,
                okhttp3.ResponseBody.create(
                    null,
                    "Unauthorized"
                )
            )
        )

        val viewModel = LoginViewModel(repository)

        viewModel.login("wrong", "wrong")
        advanceUntilIdle()

        val state = viewModel.loginState.value

        assertTrue(state is LoginState.Error)
        assertEquals(
            "Invalid student ID or first name",
            (state as LoginState.Error).message
        )
    }

    @Test
    fun loginNetworkError() = runTest(dispatcher) {
        coEvery {
            repository.login(any(), any())
        } throws IOException("No internet")

        val viewModel = LoginViewModel(repository)

        viewModel.login("8147350", "TestName")
        advanceUntilIdle()

        val state = viewModel.loginState.value

        assertTrue(state is LoginState.Error)
        assertEquals(
            "Network error. Check your internet connection.",
            (state as LoginState.Error).message
        )
    }
}
