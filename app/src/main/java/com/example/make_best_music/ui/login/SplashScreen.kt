package com.example.make_best_music.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000) // 2 seconds delay
        onSplashFinished()
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141421)),
        contentAlignment = Alignment.Center

    ) {
        // Logo in the center
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(200.sdp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )

        // Text at the bottom
        Text(
            text = "MakeBestMusic",
            color = Color.White,
            fontSize = 18.ssp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.sdp)
        )
    }
}
