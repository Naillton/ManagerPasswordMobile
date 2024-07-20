package com.nailton.managerpassword.data.API

import com.nailton.managerpassword.data.passworddata.PasswordList
import com.nailton.managerpassword.data.userdata.userDTO
import com.nailton.managerpassword.data.userdata.userLoginDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MPService {

    @POST("/api/auth/login")
    suspend fun loginUser(@Body requestBody: userLoginDTO): Response<ResponseBody>

    @POST("/api/auth/register")
    suspend fun insertUser(@Body requestBody: userDTO): Response<ResponseBody>

    @GET("api/password")
    suspend fun getPasswords(@Header("authorization") token: String): Response<PasswordList>
}