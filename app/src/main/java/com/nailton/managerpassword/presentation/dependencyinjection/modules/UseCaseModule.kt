package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.domain.repository.MPRepository
import com.nailton.managerpassword.domain.usecases.GetPasswordsUseCase
import com.nailton.managerpassword.domain.usecases.LoginUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesGetPasswordsUseCase(mpRepository: MPRepository): GetPasswordsUseCase {
        return GetPasswordsUseCase(mpRepository)
    }

    @Provides
    fun loginUserUseCase(mpRepository: MPRepository): LoginUseCase {
        return loginUserUseCase(mpRepository)
    }
}