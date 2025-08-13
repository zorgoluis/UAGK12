package com.uag.augk12.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.R
import com.uag.augk12.ui.components.HeaderTopMenu
import com.uag.augk12.viewmodel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
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
                modifier = Modifier.fillMaxWidth(),
                drawerContainerColor = MaterialTheme.colorScheme.background
            ) {
                DrawerHeader(
                    coroutineScope = coroutineScope,
                    drawerState = drawerState
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 20.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                )
                DrawerMenuItem(text = "Perfil", onClick = {
                })
                DrawerMenuItem(text = "Politica, protocolos y reglamentos", onClick = {
                })
                DrawerMenuItem(text = "Aviso de privacidad", onClick = {
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
        label = { Text(text, fontWeight = FontWeight.SemiBold, color = Color.LightGray) },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
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
    }
}