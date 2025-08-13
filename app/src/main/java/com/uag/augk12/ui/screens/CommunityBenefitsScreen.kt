package com.uag.augk12.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityBenefitsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Beneficios de la comunidad UAG") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            )
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                Row(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    BoxBenefit("Hoteles", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                    BoxBenefit("Alimentos", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                    BoxBenefit("Servicios", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                    BoxBenefit("Estilo de vida", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                }
            }
            item {
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                Row(modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    BoxBenefit("Entretenimiento", modifier = Modifier.weight(1f), icon = Icons.Filled.Home)
                    BoxBenefit("Salud y cuidado personal", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                    BoxBenefit("Ocacionales", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                    BoxBenefit("Opticas", modifier = Modifier.weight(1f),icon = Icons.Filled.Home)
                }
            }
        }
    }
}

@Composable
fun BoxBenefit(
    title: String,
    modifier: Modifier = Modifier,
    icon: ImageVector
) {
    Column(
        modifier = modifier
            .clickable {

            }
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier=Modifier.padding(5.dp))
        Text(title, fontSize = MaterialTheme.typography.titleSmall.fontSize)

    }
}