package com.uag.augk12.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.viewmodel.AuthViewModel


@Composable
fun SelectChildScreen(navController: NavController, authViewModel: AuthViewModel) {
    val children by authViewModel.children.collectAsState()

    Column(modifier = Modifier.padding(16.dp).padding(WindowInsets.safeDrawing.asPaddingValues())) {
        Text("Mis hijos", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        children.forEach { child ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 6.dp)
                    .clickable {
                        authViewModel.selectChild(child)
                        navController.navigate("home") {
                            popUpTo("selectChild") {inclusive = true  }
                        }
                    }
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(child.name)
                }
            }
        }
    }
}