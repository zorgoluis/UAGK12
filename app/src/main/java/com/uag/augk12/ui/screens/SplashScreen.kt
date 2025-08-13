package com.uag.augk12.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.uag.augk12.viewmodel.AuthViewModel

@Composable
fun SplashScreen(navController:NavController, authViewModel: AuthViewModel) {
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    LaunchedEffect(isAuthenticated) {
        kotlinx.coroutines.delay(2000)
        if(isAuthenticated) {
            navController.navigate("selectChild") { popUpTo("splash") {inclusive = true} }
        } else {
            navController.navigate("login") {popUpTo("splash"){inclusive = true} }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Splash Screen", style = MaterialTheme.typography.headlineMedium)
    }
}