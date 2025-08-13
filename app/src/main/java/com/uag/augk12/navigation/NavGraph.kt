package com.uag.augk12.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.uag.augk12.ui.screens.HomeScreen
import com.uag.augk12.ui.screens.LoginScreen
import com.uag.augk12.ui.screens.MainAppScreen
import com.uag.augk12.ui.screens.SelectChildScreen
import com.uag.augk12.ui.screens.SplashScreen
import com.uag.augk12.viewmodel.AuthViewModel

@Composable
fun NavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController, authViewModel) }
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("selectChild") { SelectChildScreen(navController, authViewModel) }
        composable("home") {
            MainAppScreen(navController, authViewModel){
                HomeScreen(navController, authViewModel)
            }
        }
    }
}