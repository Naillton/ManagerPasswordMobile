package com.nailton.managerpassword.data.datasource

import com.nailton.managerpassword.data.passworddata.PasswordList
import com.nailton.managerpassword.data.userdata.userDTO
import com.nailton.managerpassword.data.userdata.userLoginDTO
import okhttp3.ResponseBody
import retrofit2.Response

interface PasswordRemoteDataSource {

    suspend fun insertUser(requestBody: userDTO): Response<ResponseBody>
    suspend fun loginUser(requestBody: userLoginDTO): Response<ResponseBody>
    suspend fun getPasswords(headerAuth: String): Response<PasswordList>
}