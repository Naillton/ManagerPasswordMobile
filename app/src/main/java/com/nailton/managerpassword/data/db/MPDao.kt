package com.nailton.managerpassword.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nailton.managerpassword.data.passworddata.PasswordData

@Dao
interface MPDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMP(passwords: List<PasswordData>)

    @Query("DELETE FROM passwords")
    suspend fun deleteAllPasswords()

    @Query("SELECT * FROM passwords")
    suspend fun getAllPasswords(): List<PasswordData>
}