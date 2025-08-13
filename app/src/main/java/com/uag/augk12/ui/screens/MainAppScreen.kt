package com.uag.augk12.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.ui.components.HeaderTopMenu
import com.uag.augk12.viewmodel.AuthViewModel
import kotlinx.coroutines.launch


@Composable
fun MainAppScreen(
    navController:NavController,
    authViewModel: AuthViewModel,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth()
            ) {
                DrawerHeader()
                Spacer(modifier = Modifier.height(8.dp))
                DrawerMenuItem(text = "Perfil", onClick = {
                })
                DrawerMenuItem(text = "Notificaciones", onClick = {
                })
                DrawerMenuItem(text = "Otros servicios", onClick = {
                })
                DrawerMenuItem(text = "Cerrar sesión", onClick = {
                    authViewModel.logout()
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                })
            }
        }
    ) {
        Scaffold(
            topBar = {
                HeaderTopMenu(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState,
                    onNotificationClick = {},
                    onProfileClick = {}
                )
            },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    content()
                }
            }
        )
    }
}

@Composable
fun DrawerMenuItem(text: String, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(text) },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
    )
}