package com.uag.augk12.ui.screens.PPR

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*

@Composable
fun ProtocolScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Nombre", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("Última actualización", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text("Ver / Descargar", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            LazyColumn {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Protocolo de Actuación ante Emergencias",
                            color = Color(0xFF6D1B3B),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "16/05/2025",
                            modifier = Modifier.weight(0.5f),
                            fontSize = 14.sp
                        )
                        Row(
                            modifier = Modifier.weight(0.5f),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(onClick = { /* Ver documento */ }) {
                                Icon(Icons.Default.Search, contentDescription = "Ver", tint = Color(0xFF6D1B3B))
                            }
                            IconButton(onClick = { /* Descargar documento */ }) {
                                Icon(Icons.Outlined.ArrowDropDown, contentDescription = "Descargar", tint = Color(0xFF6D1B3B))
                            }
                        }
                    }
                }
            }
        }
    }
}