package com.nailton.managerpassword.domain.usecases

import com.nailton.managerpassword.data.passworddata.PasswordData
import com.nailton.managerpassword.domain.repository.MPRepository

class GetPasswordsUseCase(
    private val MPRepository: MPRepository
) {

    suspend fun getPasswords(): List<PasswordData>? = MPRepository.getPasswords()
}