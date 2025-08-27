package com.uag.augk12.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uag.augk12.ui.screens.HomeScreen
import com.uag.augk12.ui.screens.PPRScreen
import com.uag.augk12.ui.screens.PrivacityScreen
import com.uag.augk12.ui.screens.ProfileScreen
import com.uag.augk12.viewmodel.AuthViewModel

@Composable
fun NavDrawer(
    rootNavController: NavController,
    internalNavController: NavHostController,
    startScreen: String,
    paddingValues: PaddingValues,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = internalNavController,
        startDestination = startScreen,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("home") { HomeScreen(rootNavController, authViewModel) }
        composable("ppr") { PPRScreen(internalNavController) }
        composable("privacity") { PrivacityScreen(internalNavController) }
    }
}