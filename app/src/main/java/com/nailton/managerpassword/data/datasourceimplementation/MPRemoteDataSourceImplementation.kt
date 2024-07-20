package com.nailton.managerpassword.data.datasourceimplementation

import com.nailton.managerpassword.data.API.MPService
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.passworddata.PasswordList
import com.nailton.managerpassword.data.userdata.userDTO
import com.nailton.managerpassword.data.userdata.userLoginDTO
import okhttp3.ResponseBody
import retrofit2.Response

class MPRemoteDataSourceImplementation(
    private val mpService: MPService
): PasswordRemoteDataSource {
    override suspend fun insertUser(requestBody: userDTO): Response<ResponseBody> = mpService.insertUser(requestBody)
    override suspend fun loginUser(requestBody: userLoginDTO): Response<ResponseBody> = mpService.loginUser(requestBody)
    override suspend fun getPasswords(headerAuth: String): Response<PasswordList> = mpService.getPasswords(headerAuth)
}