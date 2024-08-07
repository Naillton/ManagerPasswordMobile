package com.nailton.managerpassword.domain.usecases

import com.nailton.managerpassword.domain.repository.MPRepository

class LoginUseCase(
    private val mpRepository: MPRepository
) {
    suspend fun loginUser(email: String, password: String): Boolean = mpRepository.loginUser(email, password)
}