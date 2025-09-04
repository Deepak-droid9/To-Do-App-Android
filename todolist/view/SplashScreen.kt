package com.deepakverma.todolist.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.deepakverma.todolist.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    // Delay navigation
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // UI
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.to_do_app),
            contentDescription = "app_logo",
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
//                .border(2.dp, Color.Black),
            ,
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Powered By",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Verma IT Solutions PVT LTD ®",
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(20.dp))
        }

    }
}
