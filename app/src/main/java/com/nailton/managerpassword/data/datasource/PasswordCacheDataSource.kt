package com.nailton.managerpassword.data.datasource

import com.nailton.managerpassword.data.passworddata.PasswordData

interface PasswordCacheDataSource {

    suspend fun getPasswordFromCache(): List<PasswordData>

    suspend fun savePasswordsFromCache(pass: List<PasswordData>)
}