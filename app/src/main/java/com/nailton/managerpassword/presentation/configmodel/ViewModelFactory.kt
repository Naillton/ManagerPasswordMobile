package com.nailton.managerpassword.presentation.configmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.loginUseCase

class ViewModelFactory(
    private val getPasswordsUseCase: GetPasswordsUseCase,
    private val loginUseCase: loginUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(getPasswordsUseCase, loginUseCase) as T
        }
        throw IllegalArgumentException("View Model Not Found")
    }
}