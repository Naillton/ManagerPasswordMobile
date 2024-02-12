package com.nailton.managerpassword.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TokenModel(
    private val token: String
): ViewModel() {

    private val _stateFlow = MutableStateFlow("")

    val stateFlow = _stateFlow.asStateFlow()

    fun changeToken() {
        _stateFlow.value = token
    }
}