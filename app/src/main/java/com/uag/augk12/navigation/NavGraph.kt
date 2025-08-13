package com.uag.augk12.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.uag.augk12.ui.screens.CommunityBenefitsScreen
import com.uag.augk12.ui.screens.HomeScreen
import com.uag.augk12.ui.screens.LoginScreen
import com.uag.augk12.ui.screens.MainAppScreen
import com.uag.augk12.ui.screens.OnlinePaymentScreen
import com.uag.augk12.ui.screens.ScheduleScreen
import com.uag.augk12.ui.screens.SelectChildScreen
import com.uag.augk12.ui.screens.SplashScreen
import com.uag.augk12.ui.screens.StatementAccountScreen
import com.uag.augk12.viewmodel.AuthViewModel

@Composable
fun NavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController, authViewModel) }
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("selectChild") { SelectChildScreen(navController, authViewModel) }
        composable(
            "main/{startScreen}",
            arguments = listOf(navArgument("startScreen") {
                type = NavType.StringType
                defaultValue = "home"
            })
            ) { backStackEntry ->
            val startScreen = backStackEntry.arguments?.getString("startScreen") ?: "home"
            MainAppScreen(navController, authViewModel, startScreen)
        }
        composable("schedule") { ScheduleScreen(navController, authViewModel) }
        composable("statementAccount") { StatementAccountScreen(navController, authViewModel) }
        composable("onlinePayment") { OnlinePaymentScreen(navController, authViewModel) }
        composable("communityBenefits") { CommunityBenefitsScreen(navController) }
    }
}