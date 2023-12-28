package com.nailton.managerpassword.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nailton.managerpassword.data.passworddata.PasswordData

@Database(entities = [PasswordData::class], version = 1, exportSchema = false)
abstract class MPDatabase: RoomDatabase() {

    abstract fun passwordDAO(): MPDao
}