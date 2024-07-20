package com.nailton.managerpassword.data.userdata

import kotlinx.serialization.Serializable

@Serializable
data class userDTO (
    val name: String,
    val email: String,
    val password: String
)