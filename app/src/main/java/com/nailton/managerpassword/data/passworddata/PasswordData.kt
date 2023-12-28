package com.nailton.managerpassword.data.passworddata

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "passwords")
data class PasswordData (
    @PrimaryKey
    val id: UUID,
    val app: String,
    val pass: String
)