package com.uag.augk12.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ChildModel(
    val id: Int,
    val name: String,
    val imageUrl: String
)
