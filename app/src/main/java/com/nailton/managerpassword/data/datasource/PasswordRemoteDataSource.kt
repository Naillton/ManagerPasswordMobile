package com.nailton.managerpassword.data.datasource

import com.nailton.managerpassword.data.passworddata.PasswordList
import retrofit2.Response

interface PasswordRemoteDataSource {

    suspend fun getPasswords(): Response<PasswordList>

    suspend fun loginUser(): Response<String>
}