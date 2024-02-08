package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.data.API.MPService
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.datasourceimplementation.MPRemoteDataSourceImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private var authToken: String) {

    @Singleton
    @Provides
    fun providesRemoteData(mpService: MPService): PasswordRemoteDataSource {
        return MPRemoteDataSourceImplementation(mpService, authToken)
    }
}