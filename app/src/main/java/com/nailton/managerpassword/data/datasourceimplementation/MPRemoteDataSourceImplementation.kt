package com.nailton.managerpassword.data.datasourceimplementation

import com.nailton.managerpassword.data.API.MPService
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.passworddata.PasswordList
import retrofit2.Response

class MPRemoteDataSourceImplementation(
    private val MPService: MPService,
    private var email: String,
    private var password: String
): PasswordRemoteDataSource {

    override suspend fun loginUser(): Response<String> = MPService.loginUser(email, password)
    override suspend fun getPasswords(): Response<PasswordList> = MPService.getPasswords(loginUser().body()!!)
}