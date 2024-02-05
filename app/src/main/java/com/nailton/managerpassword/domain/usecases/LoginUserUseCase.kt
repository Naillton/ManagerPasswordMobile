package com.nailton.managerpassword.domain.usecases

import com.nailton.managerpassword.domain.repository.MPRepository

class LoginUserUseCase(
    private val MPRepository: MPRepository
) {
    suspend fun loginUser(): String? = MPRepository.loginUser()
}