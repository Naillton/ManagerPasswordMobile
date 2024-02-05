package com.nailton.managerpassword.data.datasourceimplementation

import com.nailton.managerpassword.data.datasource.PasswordLocalDataSource
import com.nailton.managerpassword.data.db.MPDao
import com.nailton.managerpassword.data.passworddata.PasswordData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MPLocalDataSourceImplementation(
    private val MPDao: MPDao
): PasswordLocalDataSource {

    override suspend fun getPasswordsFromDB(): List<PasswordData> {
        val func = CoroutineScope(Dispatchers.Default).async { MPDao.getAllPasswords() }
        return func.await()
    }

    override suspend fun savePasswordsFromDB(pass: List<PasswordData>) {
        CoroutineScope(Dispatchers.IO).launch {
            MPDao.saveMP(pass)
        }
    }

    override suspend fun deletePasswordsFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            MPDao.deleteAllPasswords()
        }
    }
}