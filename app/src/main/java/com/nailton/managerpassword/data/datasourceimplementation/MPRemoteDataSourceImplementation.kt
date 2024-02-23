package com.nailton.managerpassword.data.datasourceimplementation

import com.nailton.managerpassword.data.API.MPService
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.passworddata.PasswordList
import retrofit2.Response

class MPRemoteDataSourceImplementation(
    private val MPService: MPService
): PasswordRemoteDataSource {
    override suspend fun loginUser(email: String, password: String): Response<String> = MPService.loginUser(email, password)
    override suspend fun getPasswords(): Response<PasswordList> = MPService.getPasswords()
}