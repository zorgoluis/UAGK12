package com.uag.augk12.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.uag.augk12.ui.components.TopAppChild

@Composable
fun BallotsScreen(navController:NavController) {
    Scaffold(
    topBar = { TopAppChild(onBackClick = {
        navController.popBackStack()
    }, title = "Boletas de Calificaciones") }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding)
                .fillMaxSize()
        )
    }
}