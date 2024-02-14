package com.nailton.managerpassword.screens.authentication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nailton.managerpassword.presentation.configmodel.MyViewModel
import com.nailton.managerpassword.presentation.configmodel.ViewModelFactory
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import javax.inject.Inject

class LoginConfig: AppCompatActivity(), DefaultLifecycleObserver {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var mpViewModel: MyViewModel
    private lateinit var value: String

    public override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        (application as Injector).createMPSubComponent().inject(this)
        mpViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
        Toast.makeText(
            this,
            "TA AQUI",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super<DefaultLifecycleObserver>.onCreate(owner)
        (application as Injector).createMPSubComponent().inject(this)
        mpViewModel = ViewModelProvider(this, factory)[MyViewModel::class.java]
        Toast.makeText(
            this,
            "TA AQUI 2222",
            Toast.LENGTH_LONG
        ).show()
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