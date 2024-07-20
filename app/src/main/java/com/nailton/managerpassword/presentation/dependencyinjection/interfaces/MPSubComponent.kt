package com.nailton.managerpassword.presentation.dependencyinjection.interfaces

import com.nailton.managerpassword.presentation.dependencyinjection.annotations.MPScope
import com.nailton.managerpassword.presentation.dependencyinjection.modules.MPModule
import com.nailton.managerpassword.screens.authenticated.HomeScreen
import com.nailton.managerpassword.screens.authentication.LoginScreen
import com.nailton.managerpassword.screens.authentication.RegisterScreen
import dagger.Subcomponent

@MPScope
@Subcomponent(modules = [MPModule::class])
interface MPSubComponent {
    fun inject(loginScreen: LoginScreen)
    fun injectCreateUser(registerScreen: RegisterScreen)
    fun injectHome(homeScreen: HomeScreen)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MPSubComponent
    }
}