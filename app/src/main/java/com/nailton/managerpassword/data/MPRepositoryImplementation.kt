package com.nailton.managerpassword.data

import com.nailton.managerpassword.data.datasource.PasswordCacheDataSource
import com.nailton.managerpassword.data.datasource.PasswordLocalDataSource
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.passworddata.PasswordData
import com.nailton.managerpassword.domain.repository.MPRepository

class MPRepositoryImplementation(
    private val mpRemoteDataSource: PasswordRemoteDataSource,
    private val mpLocalDataSource: PasswordLocalDataSource,
    private val mpCacheDataSource: PasswordCacheDataSource
): MPRepository {

    override suspend fun loginUser(email: String, password: String): String {
        lateinit var authToken: String
        try {
            val response = mpRemoteDataSource.loginUser(email, password)
            val body = response.body()
            if (body != null) {
                authToken = body
            }
        } catch (_:Exception) {}

        return authToken
    }

    override suspend fun getPasswords(): List<PasswordData>? {
        return getPasswordsFromCache()
    }

    private suspend fun getPasswordsFromAPI(): List<PasswordData> {
        lateinit var passwordList: List<PasswordData>

        try {
            val response = mpRemoteDataSource.getPasswords()
            val body = response.body()
            if (body != null) {
                passwordList = body.passwords
            }
        } catch (_: Exception) {}

        return passwordList
    }

    private suspend fun getPasswordsFromROOOM(): List<PasswordData> {
        lateinit var passwordList: List<PasswordData>

        try {
            passwordList = mpLocalDataSource.getPasswordsFromDB()
        } catch (_:Exception) {}

        if (passwordList.isNotEmpty()) {
            return passwordList
        } else {
            passwordList = getPasswordsFromAPI()
            mpLocalDataSource.savePasswordsFromDB(passwordList)
        }

        return passwordList
    }

    private suspend fun getPasswordsFromCache(): List<PasswordData>? {
        lateinit var passwordsList: List<PasswordData>
        try {
            passwordsList = mpCacheDataSource.getPasswordFromCache()
        } catch (_:Exception) {}

        if (passwordsList.isNotEmpty()) {
            return passwordsList
        } else {
            passwordsList = getPasswordsFromROOOM()
            mpCacheDataSource.savePasswordsFromCache(passwordsList)
        }

        return passwordsList
    }
}