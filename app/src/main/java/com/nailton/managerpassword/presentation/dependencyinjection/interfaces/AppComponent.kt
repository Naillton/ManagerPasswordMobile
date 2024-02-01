package com.nailton.managerpassword.presentation.dependencyinjection.interfaces

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [

])
interface AppComponent {

    fun managerpassSubComponent(): MPSubComponent.factory
}