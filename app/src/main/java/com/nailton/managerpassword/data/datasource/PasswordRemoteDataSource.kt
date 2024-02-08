package com.nailton.managerpassword.data.datasource

import com.nailton.managerpassword.data.passworddata.PasswordList
import retrofit2.Response

interface PasswordRemoteDataSource {

    suspend fun loginUser(email: String, password: String): Response<String>
    suspend fun getPasswords(): Response<PasswordList>
}