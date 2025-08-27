package com.uag.augk12.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uag.augk12.ui.components.TopAppChild

@Composable
fun NotificationsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppChild(onBackClick = {
                navController.popBackStack()
            }, title = "Notificaciones" )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(contentPadding)
        ){

        }
    }
}