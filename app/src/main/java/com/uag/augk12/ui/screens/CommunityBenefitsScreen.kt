package com.uag.augk12.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.uag.augk12.ui.components.TopAppChild
import com.uag.augk12.viewmodel.BenefitViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityBenefitsScreen(navController: NavController, benefitViewModel: BenefitViewModel) {
    val benefits by benefitViewModel.benefits.collectAsState()
    Scaffold(
        topBar = {
            TopAppChild(
                onBackClick = { navController.popBackStack() },
                title = "Beneficios de la comunidad UAG")
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(benefits.chunked(4)){ rowItems ->
                Row(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    rowItems.forEach { benefit ->
                        BoxBenefit(
                            benefit.title,
                            modifier = Modifier.weight(1f),
                            icon = benefit.icon,
                            onNavigate = {
                                navController.navigate("benefitsChildren/${benefit.code}")
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun BoxBenefit(
    title: String,
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onNavigate: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clickable {
                onNavigate()
            }
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary)
        Spacer(modifier=Modifier.padding(5.dp))
        Text(title, fontSize = MaterialTheme.typography.titleSmall.fontSize)

    }
}