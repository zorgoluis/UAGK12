package com.uag.augk12.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.uag.augk12.ui.components.TopAppChild
import com.uag.augk12.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlinePaymentScreen(navController: NavController, authViewModel: AuthViewModel){
    Scaffold(
        topBar = {
            TopAppChild(onBackClick = { navController.popBackStack() }, title = "Pago en linea")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
        ) {
            Text("Pago en linea")
        }
    }
}