package com.myproj.a8147350assignment2.data.repository

import com.myproj.a8147350assignment2.data.model.LoginRequest
import com.myproj.a8147350assignment2.data.remote.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) : AuthRepositoryContract {

    override suspend fun login(
        username: String,
        password: String
    ): String {
        return apiService.login(
            LoginRequest(username, password)
        ).keypass
    }
}
