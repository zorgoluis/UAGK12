package com.uag.augk12.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.R
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

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.logo_uag_tinto),
                contentDescription = "Mi SVG",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f).padding(20.dp)
            )
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}