package com.nailton.managerpassword.presentation.dependencyinjection.modules

import com.google.gson.GsonBuilder
import com.nailton.managerpassword.data.API.MPService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(
    private val baseurl: String
) {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(baseurl)
            .build()
    }

    @Singleton
    @Provides
    fun provideMPService(retrofit: Retrofit): MPService {
        return retrofit.create(MPService::class.java)
    }
}