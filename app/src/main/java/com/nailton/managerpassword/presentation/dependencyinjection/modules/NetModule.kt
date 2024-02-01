package com.nailton.managerpassword.presentation.dependencyinjection.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetModule(
    private val baseurl: String
) {

    //@Singleton
    //@Provides
    //fun providesRetrofit(): Retrofit {
    //}
}