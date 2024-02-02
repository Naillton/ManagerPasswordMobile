package com.nailton.managerpassword.data

import com.nailton.managerpassword.data.datasource.PasswordCacheDataSource
import com.nailton.managerpassword.data.datasource.PasswordLocalDataSource
import com.nailton.managerpassword.data.datasource.PasswordRemoteDataSource
import com.nailton.managerpassword.data.passworddata.PasswordData
import com.nailton.managerpassword.domain.repository.MPRepository

class MPRepositoryImplementation(
    mpRemoteDataSource: PasswordRemoteDataSource,
    mpLocalDataSource: PasswordLocalDataSource,
    mpCacheDataSource: PasswordCacheDataSource
): MPRepository {
    override suspend fun loginUser(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getPasswords(): List<PasswordData>? {
        TODO("Not yet implemented")
    }
}