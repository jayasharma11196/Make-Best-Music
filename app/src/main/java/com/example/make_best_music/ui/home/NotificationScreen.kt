package com.example.make_best_music.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun NotificationScreen(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141421))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.sdp, vertical = 12.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                }
                
                Text(
                    text = "Notifications 0",
                    color = Color.White,
                    fontSize = 18.ssp,
                    fontWeight = FontWeight.Bold
                )
                
                TextButton(onClick = { /* Clear All */ }) {
                    Text(
                        text = "Clear All",
                        color = Color.Gray,
                        fontSize = 14.ssp
                    )
                }
            }
            
            // Empty State
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Vinyl Record Placeholder
                Image(
                    painter = painterResource(id = com.example.make_best_music.R.drawable.no_record),
                    contentDescription = null,
                    modifier = Modifier.size(120.sdp)
                )
                
                Spacer(modifier = Modifier.height(20.sdp))
                
                Text(
                    text = "No notifications yet",
                    color = Color.Gray,
                    fontSize = 16.ssp
                )
            }
        }
    }
}
