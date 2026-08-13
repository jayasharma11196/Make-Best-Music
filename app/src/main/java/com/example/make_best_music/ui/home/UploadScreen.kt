package com.example.make_best_music.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun UploadScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(horizontal = 16.sdp)
    ) {
        Spacer(modifier = Modifier.height(20.sdp))

        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            Text(
                text = "Upload",
                color = Color.White,
                fontSize = 18.ssp,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.width(8.sdp))
            
            // Yearly-Mem Tag
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.sdp))
                    .background(Color(0xFF2E1C2E))
                    .padding(horizontal = 8.sdp, vertical = 2.sdp)
            ) {
                Text(
                    text = "Yearly-Mem",
                    color = Color(0xFFFF2D81),
                    fontSize = 8.ssp
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(48.sdp)) // To balance the back button
        }

        Spacer(modifier = Modifier.height(60.sdp))

        // Upload Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.sdp)
                .clip(RoundedCornerShape(16.sdp))
                .background(Color(0xFF1C1C2E))
                .border(
                    width = 1.dp,
                    color = Color.Gray.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(16.sdp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Cloud Icon with Gradient
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(80.sdp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CloudUpload,
                        contentDescription = null,
                        tint = Color(0xFFFF2D81),
                        modifier = Modifier.size(50.sdp)
                    )
                }

                Spacer(modifier = Modifier.height(10.sdp))

                Text(
                    text = "Click to upload",
                    color = Color.White,
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.sdp))

                Text(
                    text = "Audio: MP3/WAV/M4A, 3s-8min",
                    color = Color.Gray,
                    fontSize = 12.ssp
                )

                Spacer(modifier = Modifier.height(20.sdp))

                Text(
                    text = "Annual subscription exclusive",
                    color = Color.Gray.copy(alpha = 0.6f),
                    fontSize = 10.ssp
                )
            }
        }

        Spacer(modifier = Modifier.height(40.sdp))

        Text(
            text = "Upload audio for recreation.",
            color = Color.Gray,
            fontSize = 12.ssp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        // Save Button
        Button(
            onClick = { /* Handle Save */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.sdp)
                .padding(bottom = 10.sdp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(24.sdp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFF2D81), Color(0xFFFF5F6D))
                        ),
                        shape = RoundedCornerShape(24.sdp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Save",
                        color = Color.White,
                        fontSize = 16.ssp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.sdp))
                    Text(
                        text = "✦ 1",
                        color = Color.White,
                        fontSize = 14.ssp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.sdp))
    }
}
