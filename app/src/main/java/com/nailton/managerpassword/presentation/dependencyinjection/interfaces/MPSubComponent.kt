package com.nailton.managerpassword.presentation.dependencyinjection.interfaces

import com.nailton.managerpassword.presentation.dependencyinjection.annotations.MPScope
import com.nailton.managerpassword.presentation.dependencyinjection.modules.MPModule
import com.nailton.managerpassword.screens.authentication.LoginScreen
import dagger.Subcomponent

@MPScope
@Subcomponent(modules = [MPModule::class])
interface MPSubComponent {
    fun inject(loginScreen: LoginScreen)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MPSubComponent
    }
}