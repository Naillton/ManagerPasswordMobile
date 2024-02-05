package com.nailton.managerpassword.data.API

import com.nailton.managerpassword.data.passworddata.PasswordList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MPService {

    @POST("/api/auth/login")
    suspend fun loginUser(email: String, password: String): Response<String>

    @GET("/api/password")
    suspend fun getPasswords(@Header("authorization") token: String): Response<PasswordList>
}