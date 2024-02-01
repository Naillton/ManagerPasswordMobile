package com.nailton.managerpassword.presentation.configmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.LoginUserUseCase

class MyViewModel(
    private val getPasswordsUseCase: GetPasswordsUseCase,
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    fun getPasswords() = liveData {
        val passwordList = getPasswordsUseCase.getPasswords()
        emit(passwordList)
    }

    fun loginUser() = liveData {
        val login = loginUserUseCase.loginUser()
        emit(login)
    }
}