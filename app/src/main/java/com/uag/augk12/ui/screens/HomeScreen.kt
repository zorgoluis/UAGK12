package com.uag.augk12.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                .height(120.dp)
                .background(color = MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text("Banner Principal", style = MaterialTheme.typography.bodyLarge)
            }
        }

        item {
            // Servicios grid placeholder (puedes usar LazyVerticalGrid si quieres)
            Text("Servicios", style = MaterialTheme.typography.titleMedium)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ServiceBox("Boletas", Modifier.weight(1f))
                ServiceBox("Asistencia", Modifier.weight(1f))
                ServiceBox("Horarios", Modifier.weight(1f))
                ServiceBox("Calendario", Modifier.weight(1f))
            }
        }

        item {
            // Eventos
            Text("Eventos", style = MaterialTheme.typography.titleMedium)
            PlaceholderCard("Evento 1")
            PlaceholderCard("Evento 2")
        }

        item {
            // Anuncios
            Text("Anuncios", style = MaterialTheme.typography.titleMedium)
            PlaceholderCard("Comunicado 1")
            PlaceholderCard("Aviso importante")
        }

        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}

@Composable
fun DrawerHeader() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // perfil avatar vacío
        Box(modifier = Modifier
            .size(72.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Nombre del padre", style = MaterialTheme.typography.titleMedium)
        Text("correo@ejemplo.com", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun ServiceBox(text: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(72.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text)
        }
    }
}

@Composable
fun PlaceholderCard(text: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)) {
        Box(contentAlignment = Alignment.Center) {
            Text(text)
        }
    }
}