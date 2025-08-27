package com.uag.augk12.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uag.augk12.data.models.DestinationPPR

@Composable
fun TabRow(
    navHostController: NavHostController,
    destinations: List<DestinationPPR>,
    selectedDestination: Int,
    onTabSelected: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(35.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        destinations.forEachIndexed { index, destination ->
            Surface(
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                color = if (selectedDestination == index) Color(0xFFE0E0E0) else MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        navHostController.navigate(destination.route)
                        onTabSelected(index)
                    }.border(BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = destination.label,
                        color = if (selectedDestination == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}