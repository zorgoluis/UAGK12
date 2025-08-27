package com.uag.augk12.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uag.augk12.data.models.DestinationPPR
import com.uag.augk12.navigation.PPRNavHost
import com.uag.augk12.ui.components.TabRow

@Composable
fun PPRScreen(navController: NavHostController) {
    val navHostController = rememberNavController()
    val startDestination = DestinationPPR.POLICIES
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Scaffold { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp)
            ){
                Text("Visor de documentos", style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.height(20.dp))
            TabRow(
                navHostController = navHostController,
                destinations = DestinationPPR.entries,
                selectedDestination = selectedDestination,
                onTabSelected = { selectedDestination = it}
            )
            PPRNavHost(navHostController, startDestination)
        }
    }
}