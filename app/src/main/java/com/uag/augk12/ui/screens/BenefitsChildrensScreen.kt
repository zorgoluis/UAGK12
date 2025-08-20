package com.uag.augk12.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.uag.augk12.data.models.BenefitChildModel
import com.uag.augk12.ui.components.TopAppChild
import com.uag.augk12.viewmodel.BenefitViewModel

@Composable
fun BenefitChildrensScreen(navController: NavController, benefitViewModel: BenefitViewModel, type:String) {
    val benefit = benefitViewModel.getBenefitByCode(type)
    var showModal by remember { mutableStateOf(false) }
    var selectedChild by remember { mutableStateOf<BenefitChildModel?>(null) }

    Scaffold(
        topBar = {
            TopAppChild(onBackClick = {navController.popBackStack()}, title = benefit?.title ?: "")
        }
    ) { paddingValues ->
        DialogChildDetail(
            onDismiss = {showModal = false},
            showDialog = showModal,
            child = selectedChild
        )
        LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            item {
                Text("Beneficios > ${benefit?.title}", modifier = Modifier.padding(horizontal = 10.dp))
            }
            items(benefit?.children?.chunked(4) ?: emptyList()) { rowItem ->
                Row(
                    modifier = Modifier.padding(5.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItem.forEach { child ->
                        ItemChild(
                            modifier = Modifier.weight(1f),
                            icon = child.icon,
                            onClick = {
                                showModal = true
                                selectedChild = child
                            }
                        )
                    }

                }
                Spacer(
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
    }
}

@Composable
fun ItemChild(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick:() -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.size(60.dp),
            ) {
                Icon(icon, tint = MaterialTheme.colorScheme.primary, contentDescription = "")
            }
            Row {
                Button(
                    onClick = onClick
                ){
                    Text("Ver más", fontSize= MaterialTheme.typography.bodySmall.fontSize)
                }
            }
        }
    }
}


@Composable
fun DialogChildDetail(
    onDismiss: () -> Unit,
    showDialog: Boolean,
    child: BenefitChildModel?
) {
    if(showDialog && child != null){
        Dialog(onDismissRequest = { onDismiss() }){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ){
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    shape = RoundedCornerShape(0.dp),
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(10.dp),
                    ) {
                        Text("${child.title}")
                        Text("todo esto es una opcion")
                    }
                }
            }
        }
    }
}