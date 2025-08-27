package com.uag.augk12.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.uag.augk12.data.models.DestinationPPR
import com.uag.augk12.ui.screens.PPR.PoliciesScreen
import com.uag.augk12.ui.screens.PPR.ProtocolScreen
import com.uag.augk12.ui.screens.PPR.RegulationScreen

@Composable
fun PPRNavHost(
    navController: NavHostController,
    startDestination: DestinationPPR,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        DestinationPPR.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    DestinationPPR.POLICIES -> PoliciesScreen()
                    DestinationPPR.PROTOCOLS -> ProtocolScreen()
                    DestinationPPR.REGULATIONS -> RegulationScreen()
                }
            }
        }
    }
}