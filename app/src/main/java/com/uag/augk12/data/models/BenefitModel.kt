package com.uag.augk12.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BenefitModel(
    val title: String,
    val code: String,
    val icon: ImageVector,
    val children: List<BenefitChildModel> = emptyList()
)

data class BenefitChildModel(
    val title: String,
    val code: String,
    val icon: ImageVector
)
