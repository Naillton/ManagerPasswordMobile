package com.nailton.managerpassword.screens.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nailton.managerpassword.presentation.configmodel.MyViewModel
import com.nailton.managerpassword.presentation.configmodel.ViewModelFactory
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import javax.inject.Inject

class LoginScreen : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var mpViewModel: MyViewModel
    private lateinit var value: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as Injector).createMPSubComponent().inject(this)
        mpViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
    }

    fun login(email: String, pass: String): String {
        mpViewModel.loginUser(email, pass).observe(this, Observer {
            if (it != null) {
                value = it.ifEmpty {
                    "Ta vazio"
                }
                value = it
            } else {
                value = "Valor nulo"
            }
        })
        return value
    }
}
