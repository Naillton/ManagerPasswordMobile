package com.nailton.managerpassword.presentation.configmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.InserUserUseCase
import com.nailton.managerpassword.domain.usecases.LoginUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MyViewModel(
    private val getPasswordsUseCase: GetPasswordsUseCase,
    private val loginUseCase: LoginUseCase,
    private val insertUserUseCase: InserUserUseCase
): ViewModel() {

    fun getPasswords() = liveData {
        val passwordList = getPasswordsUseCase.getPasswords()
        emit(passwordList)
    }


    fun loginUser(email: String, password: String) = liveData {
        val login = loginUseCase.loginUser(email, password)
        emit(login)
    }

    fun insertUser(name: String, email: String, password: String) = liveData {
        val insertUser = insertUserUseCase.insertUser(name, email, password)
        emit(insertUser)
    }
}