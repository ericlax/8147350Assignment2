package com.myproj.a8147350assignment2.data.repository

interface AuthRepositoryContract {
    suspend fun login(
        username: String,
        password: String
    ): String
}
