package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.LoginUseCase
import com.nailton.managerpassword.presentation.configmodel.ViewModelFactory
import com.nailton.managerpassword.presentation.dependencyinjection.annotations.MPScope
import dagger.Module
import dagger.Provides

@Module
class MPModule {

    @MPScope
    @Provides
    fun provideViewModelFactory(
        getPasswordsUseCase: GetPasswordsUseCase,
        loginUseCase: LoginUseCase
    ): ViewModelFactory {
        return ViewModelFactory(getPasswordsUseCase, loginUseCase)
    }
}