package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.domain.repository.MPRepository
import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.LoginUserUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesLoginUseCase(mpRepository: MPRepository): LoginUserUseCase {
        return LoginUserUseCase(mpRepository)
    }

    @Provides
    fun providesGetPasswordsUseCase(mpRepository: MPRepository): GetPasswordsUseCase {
        return GetPasswordsUseCase(mpRepository)
    }
}