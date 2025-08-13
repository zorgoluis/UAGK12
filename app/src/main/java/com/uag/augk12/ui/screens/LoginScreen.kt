package com.uag.augk12.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uag.augk12.R
import com.uag.augk12.viewmodel.AuthViewModel


@Composable
fun LoginScreen(navController: NavController, authViewModel:AuthViewModel) {
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val context = LocalContext.current
    val (versionName, versionCode) = getAppVersionInfo(context)

    LaunchedEffect(isAuthenticated) {
        if(isAuthenticated) {
            navController.navigate("selectChild") {
                popUpTo("login") {inclusive = true}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo UAG",
            modifier = Modifier
                .height(100.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .size(180.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("Imagen aquí", color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Versión $versionName.$versionCode",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.login("root", "123456")
                navController.navigate("selectChild")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000))
        ) {
            Text(
                text = "INICIAR SESIÓN",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

//        Button(
//            onClick = {
//                authViewModel.login(username, password)
//                navController.navigate("selectChild")
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Iniciar Sesión")
//        }
    }
}

fun getAppVersionInfo(context: Context): Pair<String, Int> {
    return try {
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.PackageInfoFlags.of(0)
            )
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.getPackageInfo(context.packageName, 0)
        }
        val versionName = packageInfo.versionName ?: "Desconocida"
        val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode.toInt()
        } else {
            @Suppress("DEPRECATION")
            packageInfo.versionCode
        }
        Pair(versionName, versionCode)
    } catch (e: PackageManager.NameNotFoundException) {
        Pair("Desconocida", -1)
    }
}