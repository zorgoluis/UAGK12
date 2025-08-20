package com.uag.augk12.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.uag.augk12.ui.components.TopAppChild
import com.uag.augk12.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatementAccountScreen(navController: NavController, authViewModel: AuthViewModel){
    Scaffold(
        topBar = {
            TopAppChild(onBackClick = { navController.popBackStack() }, title = "Estado de cuenta")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ){
            Text("Estado de cuenta")
        }
    }
}