package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.nailton.managerpassword.data.datasource.PasswordLocalDataSource
import com.nailton.managerpassword.data.datasourceimplementation.MPLocalDataSourceImplementation
import com.nailton.managerpassword.data.db.MPDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun providesLocalData(mpDatabase: MPDatabase): PasswordLocalDataSource {
        return MPLocalDataSourceImplementation(mpDatabase.passwordDAO())
    }
}