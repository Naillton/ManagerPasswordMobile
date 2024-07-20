package com.nailton.managerpassword.domain.usecases

import com.nailton.managerpassword.domain.repository.MPRepository

class InserUserUseCase(
    private val mpRepository: MPRepository
) {
    suspend fun insertUser(name: String, email: String, password: String): String? = mpRepository.inserUser(name, email, password)
}