package com.nailton.managerpassword.data

import android.util.Log
import com.nailton.managerpassword.data.datasource.PasswordCacheDataSource
import com.nailton.managerpassword.data.datasource.PasswordLocalDataSource
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.passworddata.PasswordData
import com.nailton.managerpassword.data.userdata.userDTO
import com.nailton.managerpassword.data.userdata.userLoginDTO
import com.nailton.managerpassword.domain.repository.MPRepository

class MPRepositoryImplementation(
    private val mpRemoteDataSource: PasswordRemoteDataSource,
    private val mpLocalDataSource: PasswordLocalDataSource,
    private val mpCacheDataSource: PasswordCacheDataSource
): MPRepository {
    override suspend fun inserUser(name: String, email: String, password: String): String? {
        try {
            val user = userDTO(name, email, password)
            val response = mpRemoteDataSource.insertUser(user)
            if (response.raw().isSuccessful) {
                response.body()?.charStream().let {
                    if (it != null) {
                        return it.readText()
                    }
                }
            }
            return "Failed to create user"
        } catch (e: Exception) {
            return e.message.toString()
        }
    }

    override suspend fun loginUser(email: String, password: String): Boolean {
        try {
            val user = userLoginDTO(email, password)
            val response = mpRemoteDataSource.loginUser(user)
            if (response.raw().isSuccessful) {
                response.body()?.charStream()?.let {
                    HeaderStore.authToken = it.readText()
                    return true
                }
            }
            return false
        } catch (e:Exception) {
            return false
        }
    }

    override suspend fun getPasswords(): List<PasswordData>? {
        return getPasswordsFromCache()
    }

    private suspend fun getPasswordsFromAPI(): List<PasswordData> {
        lateinit var passwordList: List<PasswordData>
        val auth = HeaderStore.authToken
        try {
            val response = auth?.let { mpRemoteDataSource.getPasswords(it) }
            val body = response?.body()
            if (body != null) {
                Log.i("TAGB", body.passwords.toString())
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