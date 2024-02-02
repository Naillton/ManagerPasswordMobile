package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.data.datasource.PasswordCacheDataSource
import com.nailton.managerpassword.data.datasourceimplementation.MPCacheDataSourceImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun providesCacheData(): PasswordCacheDataSource {
        return MPCacheDataSourceImplementation()
    }
}