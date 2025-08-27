package com.uag.augk12.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.*
import com.uag.augk12.R
import com.uag.augk12.navigation.NavDrawer
import com.uag.augk12.ui.components.HeaderTopMenu
import com.uag.augk12.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainAppScreen(
    navController:NavController,
    authViewModel: AuthViewModel,
    startScreen: String
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val internalNavController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(),
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                DrawerHeader(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState,
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
                DrawerMenuItem(text = "Perfil", onClick = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate("profile", {
                        restoreState = true
                        launchSingleTop = true
                    })
                })
                DrawerMenuItem(text = "Politica, protocolos y reglamentos", onClick = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    internalNavController.navigate("ppr", {
                        restoreState = true
                        launchSingleTop = true
                    })
                })
                DrawerMenuItem(text = "Aviso de privacidad", onClick = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    internalNavController.navigate("privacity", {
                        restoreState = true
                        launchSingleTop = true
                    })
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
                    onNotificationClick = {
                        navController.navigate("notifications")
                    },
                    onProfileClick = {
                        navController.navigate("profile")
                    }
                )
            }
        ){ paddingValues ->
            NavDrawer(
                rootNavController = navController,
                internalNavController = internalNavController,
                startScreen = startScreen,
                paddingValues = paddingValues,
                authViewModel = authViewModel
            )
        }
    }
}

@Composable
fun DrawerMenuItem(text: String, onClick: () -> Unit) {
    NavigationDrawerItem(
        label = { Text(text, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.42f)) },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
    )
}

@Composable
fun DrawerHeader(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
           verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo UAG",
                modifier = Modifier
                    .height(50.dp),
                contentScale = ContentScale.Fit
            )
            IconButton(onClick = {
                coroutineScope.launch { drawerState.close() }
            }) {
                Icon(Icons.Default.Home, contentDescription = "Home", tint = MaterialTheme.colorScheme.primary)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Otros servicios", style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ))
        }
    }
}