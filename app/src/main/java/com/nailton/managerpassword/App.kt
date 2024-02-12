package com.nailton.managerpassword

import android.app.Application
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.AppComponent
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.DaggerAppComponent
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.MPSubComponent
import com.nailton.managerpassword.presentation.dependencyinjection.modules.AppModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.NetModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.RemoteDataModule
import com.nailton.managerpassword.viewModel.TokenModel

class App: Application(), Injector {
    private lateinit var appComponent: AppComponent
    private lateinit var modelToken: TokenModel
    private val baseURL = "http://localhost:8080/"
    private val authToken = modelToken.stateFlow.value

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(baseURL))
            .remoteDataModule(RemoteDataModule(authToken))
            .build()

    }

    override fun createMPSubComponent(): MPSubComponent {
        return appComponent.managerpassSubComponent().create()
    }
}