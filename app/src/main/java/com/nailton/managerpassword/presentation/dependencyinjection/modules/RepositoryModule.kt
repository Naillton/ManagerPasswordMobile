package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.data.API.MPService
import com.nailton.managerpassword.data.MPRepositoryImplementation
import com.nailton.managerpassword.data.datasource.PasswordCacheDataSource
import com.nailton.managerpassword.data.datasource.PasswordLocalDataSource
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.domain.repository.MPRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMPRepository(
        mpRemoteDataSource: PasswordRemoteDataSource,
        mpCacheDataSource: PasswordCacheDataSource,
        mpLocalDataSource: PasswordLocalDataSource
    ): MPRepository {
        return MPRepositoryImplementation(
            mpRemoteDataSource,
            mpLocalDataSource,
            mpCacheDataSource
        )
    }
}