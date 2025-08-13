package com.uag.augk12.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserModel(
    val username: String,
    val password: String
)
