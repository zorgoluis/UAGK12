package com.uag.augk12.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.data.models.DownloadStatus
import com.uag.augk12.ui.components.TopAppChild
import com.uag.augk12.viewmodel.DownloadViewModel

@Composable
fun CalendarScreen(navController:NavController, viewModel: DownloadViewModel) {
    val status by viewModel.status.collectAsState()
    Scaffold(
        topBar = {
            TopAppChild(
                onBackClick = {
                    navController.popBackStack()
                },
                title = "Calendario escolar"
            )
        }
    ) { paddinValues ->
        Column(
            modifier = Modifier.padding(paddinValues).padding(horizontal = 20.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.surface),
                onClick = {
                    viewModel.startDownload("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf")
                }
            ) { Text("Descargar archivo") }
            Spacer(modifier = Modifier.height(16.dp))
            when(status) {
                is DownloadStatus.Success -> Text("Descarga exitosa: ${(status as DownloadStatus.Success).file.path}")
                is DownloadStatus.Failed -> Text("Error: ${(status as DownloadStatus.Failed).error.message}")
                is DownloadStatus.Canceled -> Text("Descarga cancelada")
                null -> Text("Espernaod descarga")
            }
        }
    }
}