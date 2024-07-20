package com.nailton.managerpassword.domain.repository

import com.nailton.managerpassword.data.passworddata.PasswordData

interface MPRepository {

    suspend fun  inserUser(name: String, email: String, password: String): String?
    suspend fun loginUser(email: String, password: String): Boolean
    suspend fun getPasswords(): List<PasswordData>?
}