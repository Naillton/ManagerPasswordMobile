package com.nailton.managerpassword.data.userdata

import kotlinx.serialization.Serializable

@Serializable
data class userLoginDTO(
    val email: String,
    val password: String
)