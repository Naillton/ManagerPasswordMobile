package com.nailton.managerpassword.presentation.dependencyinjection.interfaces

import com.nailton.managerpassword.presentation.dependencyinjection.modules.AppModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.CacheDataModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.DatabaseModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.LocalDataModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.NetModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.RemoteDataModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.RepositoryModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CacheDataModule::class,
    DatabaseModule::class,
    LocalDataModule::class,
    NetModule::class,
    RemoteDataModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface AppComponent {

    fun managerpassSubComponent(): MPSubComponent.Factory
}