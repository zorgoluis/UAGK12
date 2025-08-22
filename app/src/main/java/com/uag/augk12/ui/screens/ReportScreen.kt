package com.uag.augk12.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uag.augk12.data.models.Activity
import com.uag.augk12.ui.components.TopAppChild

@Composable
fun ReportScreen(navController: NavController) {
    val actividades = remember {
        mutableStateListOf(
            Activity("Materia 1", "Actividad 1", "30/05/2025", true),
            Activity("Materia 2", "Actividad 2", "30/05/2025", false),
        )
    }

    Scaffold(
        topBar = {
            TopAppChild(
                onBackClick = {
                    navController.popBackStack()
                },
                title = "Reporte de desempeño"
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).padding(horizontal = 20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Column {
                    Text("Desempeño", fontSize = MaterialTheme.typography.headlineSmall.fontSize )
                    Text("Clave: xxxx", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 5.dp))
                    Text("Nombre: xxxx", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 5.dp))
                    Text("Grado: xxxx", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 5.dp))
                    Text("Grupo: xxxx", color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(vertical = 5.dp))
                }
            }
            Spacer(Modifier.height(16.dp))
            Text(
                "Fecha de última actualización de las tareas: 25/08/2024",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEDEDED))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Materia", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primary)
                Text("Trabajo/Actividad", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primary)
                Text("Fecha límite", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primary)
                Text("Revisado", style = MaterialTheme.typography.titleSmall, modifier = Modifier.weight(0.5f), color = MaterialTheme.colorScheme.primary)
            }
            LazyColumn {
                items(actividades) { actividad ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(actividad.materia, modifier = Modifier.weight(1f))
                        Text(actividad.actividad, modifier = Modifier.weight(1f))
                        Text(actividad.fechaEntrega, modifier = Modifier.weight(1f))
                        Checkbox(
                            checked = actividad.revisado,
                            onCheckedChange = { isChecked ->
                                actividad.revisado = isChecked
                            },
                            modifier = Modifier.weight(0.5f)
                        )
                    }
                }
            }
        }
    }
}