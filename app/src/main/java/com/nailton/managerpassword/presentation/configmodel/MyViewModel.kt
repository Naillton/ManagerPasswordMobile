package com.nailton.managerpassword.presentation.configmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.LoginUseCase

class MyViewModel(
    private val getPasswordsUseCase: GetPasswordsUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    fun getPasswords() = liveData {
        val passwordList = getPasswordsUseCase.getPasswords()
        emit(passwordList)
    }

    fun loginUser(email: String, password: String) = liveData {
        val login = loginUseCase.loginUser(email, password)
        emit(login)
    }
}