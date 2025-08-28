package com.uag.augk12.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.viewmodel.AuthViewModel

@Composable
fun HomeScreen(navController: NavController, authViewModel:AuthViewModel) {
    val selectedChild by authViewModel.selectedChild.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(313f / 60f)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text("Banner Principal", style = MaterialTheme.typography.bodyLarge)
            }
        }

        item {
            // Servicios grid placeholder (puedes usar LazyVerticalGrid si quieres)
            Text("Servicios", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ServiceBox("Boletas", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("ballots")
                })
                ServiceBox("Asistencia y Puntualidad", modifier = Modifier.weight(1f), onClick = {

                } )
                ServiceBox("Horarios", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("schedule")
                })
                ServiceBox("Calendario Escolar", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("calendar")
                })
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                ServiceBox("Estado de cuenta", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("statementAccount")
                })
                ServiceBox("Pago en linea", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("onlinePayment")
                } )
                ServiceBox("Beneficios", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("communityBenefits")
                })
                ServiceBox("Reporte de desempeño", modifier = Modifier.weight(1f), onClick = {
                    navController.navigate("report")
                })
            }
        }

        item {
            PlaceholderCard("Evento 1")
            Spacer(modifier = Modifier.height(34.dp))
            PlaceholderCard("Anuncios")
            Spacer(modifier = Modifier.height(34.dp))
            PlaceholderCard("Sitios de interes")
        }
    }
}



@Composable
fun ServiceBox(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(8.dp)
                .clickable {onClick()},
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("📦")
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text, style = MaterialTheme.typography.labelSmall, maxLines = 2)
    }
}

@Composable
fun PlaceholderCard(text: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(313f / 60f)) {
        Box(contentAlignment = Alignment.Center) {
            Text(text)
        }
    }
}