package com.myproj.a8147350assignment2.data.remote

import com.myproj.a8147350assignment2.data.model.LoginRequest
import com.myproj.a8147350assignment2.data.model.LoginResponse
import com.myproj.a8147350assignment2.data.model.DashboardResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("footscray/auth")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(
        @Path("keypass") keypass: String
    ): DashboardResponse
}
