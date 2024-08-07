package com.nailton.managerpassword

import android.app.Application
import android.util.Log
import com.nailton.managerpassword.data.HeaderStore
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.AppComponent
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.DaggerAppComponent
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.Injector
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.MPSubComponent
import com.nailton.managerpassword.presentation.dependencyinjection.modules.AppModule
import com.nailton.managerpassword.presentation.dependencyinjection.modules.NetModule

class App: Application(), Injector {
    private lateinit var appComponent: AppComponent
    private val baseURL = "http://192.168.1.106:8080"

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(baseURL))
            .build()

    }

    override fun createMPSubComponent(): MPSubComponent {
        return appComponent.managerpassSubComponent().create()
    }
}