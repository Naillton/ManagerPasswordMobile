package com.nailton.managerpassword.presentation.dependencyinjection.modules

import android.content.Context
import androidx.room.Room
import com.nailton.managerpassword.data.db.MPDao
import com.nailton.managerpassword.data.db.MPDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesMPDatabase(context: Context): MPDatabase {
        return Room.databaseBuilder(
            context,
            MPDatabase::class.java,
            "LocalPasswords"
        ).build()
    }

    @Singleton
    @Provides
    fun providesMPDAO(mpDatabase: MPDatabase): MPDao {
        return mpDatabase.passwordDAO()
    }
}