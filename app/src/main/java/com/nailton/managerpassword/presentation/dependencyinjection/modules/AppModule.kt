package com.nailton.managerpassword.presentation.dependencyinjection.modules

import android.content.Context
import com.nailton.managerpassword.presentation.dependencyinjection.interfaces.MPSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MPSubComponent::class])
class AppModule(
    private val context: Context
) {

    @Singleton
    @Provides
    fun provideContextApplication(): Context {
        return context.applicationContext
    }
}