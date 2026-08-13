package com.example.make_best_music.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditDetailsScreen(onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Credit Details",
                        color = Color.White,
                        fontSize = 18.ssp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF141421)
                )
            )
        },
        containerColor = Color(0xFF141421)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.sdp))

            // Remaining Credits Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.sdp))
                    .background(Color(0xFFFF2D81).copy(alpha = 0.2f))
                    .padding(horizontal = 20.sdp, vertical = 10.sdp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFFFF2D81),
                        modifier = Modifier.size(16.sdp)
                    )
                    Spacer(modifier = Modifier.width(8.sdp))
                    Text(
                        text = "Remaining Credits: 3",
                        color = Color(0xFFFF2D81),
                        fontSize = 14.ssp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.sdp))

            // Credit Details Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.sdp))
                    .background(Color(0xFF1C1C2E))
                    .padding(24.sdp),
                verticalArrangement = Arrangement.spacedBy(20.sdp)
            ) {
                CreditInfoRow("Date obtained:", "10 Aug 2026")
                CreditInfoRow("Source:", "register")
                CreditInfoRow("Earned:", "3")
                CreditInfoRow("Remaining:", "3")
                CreditInfoRow("Due Date:", "1 Jan 2099")
            }
        }
    }
}

@Composable
fun CreditInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.ssp
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 16.ssp,
            fontWeight = FontWeight.Bold
        )
    }
}
