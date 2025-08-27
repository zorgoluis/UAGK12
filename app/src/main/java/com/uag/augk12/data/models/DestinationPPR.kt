package com.uag.augk12.data.models

enum class DestinationPPR(
    val route: String,
    val label: String,
) {
    POLICIES("policies", "Politicas"),
    PROTOCOLS("protocols", "Protocolos"),
    REGULATIONS("regulations", "Reglamentos")
}