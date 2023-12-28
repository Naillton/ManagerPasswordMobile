package com.nailton.managerpassword.data.datasource

import com.nailton.managerpassword.data.passworddata.PasswordData

interface PasswordLocalDataSource {

    suspend fun getPasswordsFromDB(): List<PasswordData>

    suspend fun savePasswordsFromDB(pass: List<PasswordData>)

    suspend fun deletePasswordsFromDB()
}