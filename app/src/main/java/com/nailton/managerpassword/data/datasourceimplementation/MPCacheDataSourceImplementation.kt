package com.nailton.managerpassword.data.datasourceimplementation

import com.nailton.managerpassword.data.datasource.PasswordCacheDataSource
import com.nailton.managerpassword.data.passworddata.PasswordData

class MPCacheDataSourceImplementation: PasswordCacheDataSource {
    private var MPList = ArrayList<PasswordData>()

    override suspend fun getPasswordFromCache(): List<PasswordData> {
        return this.MPList
    }

    override suspend fun savePasswordsFromCache(pass: List<PasswordData>) {
        this.MPList.clear()
        this.MPList = ArrayList(pass)
    }
}