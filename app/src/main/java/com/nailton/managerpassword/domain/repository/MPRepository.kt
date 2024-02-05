package com.nailton.managerpassword.domain.repository

import com.nailton.managerpassword.data.passworddata.PasswordData

interface MPRepository {

    suspend fun loginUser(): String?
    suspend fun getPasswords(): List<PasswordData>?
}