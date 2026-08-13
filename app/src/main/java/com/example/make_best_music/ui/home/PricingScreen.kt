package com.example.make_best_music.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun PricingScreen(onBackClick: () -> Unit) {
    var selectedPlan by remember { mutableIntStateOf(0) } // 0 for Weekly, 1 for Annual

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141421))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header with Overlapping Images
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.sdp)
            ) {
                // Background Collage (Placeholder Images)
                Row(modifier = Modifier.fillMaxSize().padding(top = 20.sdp)) {
                    Image(
                        painter = painterResource(id = R.drawable.iv_music),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.sdp)
                            .rotate(-10f)
                            .offset(x = (-20).sdp)
                            .clip(RoundedCornerShape(16.sdp)),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.iv_music),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .height(220.sdp)
                            .rotate(5f)
                            .offset(y = (-20).sdp)
                            .clip(RoundedCornerShape(16.sdp)),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = R.drawable.iv_music),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .height(200.sdp)
                            .rotate(10f)
                            .offset(x = 20.sdp)
                            .clip(RoundedCornerShape(16.sdp)),
                        contentScale = ContentScale.Crop
                    )
                }

                // Gradient Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xFF141421)),
                                startY = 100f
                            )
                        )
                )

                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.padding(16.sdp).align(Alignment.TopStart)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.sdp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Get Full Access",
                        color = Color.White,
                        fontSize = 32.ssp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "Join millions of creators",
                        color = Color(0xFFFF2D81),
                        fontSize = 16.ssp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Benefits List
            Column(
                modifier = Modifier.padding(horizontal = 24.sdp),
                verticalArrangement = Arrangement.spacedBy(8.sdp)
            ) {
                BenefitItem("Unlock all AI styles (100+)")
                BenefitItem("Generate music up to 8 min")
                BenefitItem("AI Cover Song")
                BenefitItem("Commercial License")
                BenefitItem("Priority Generation & Fast Downloads")
            }

            Spacer(modifier = Modifier.height(30.sdp))

            // Plans
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.sdp)
                    .padding(top = 10.sdp), // Add padding to top to accommodate badge offset
                verticalArrangement = Arrangement.spacedBy(16.sdp)
            ) {
                // Weekly Plan
                PlanItem(
                    title = "Weekly Plan",
                    price = "$ 5.99",
                    period = "/Week",
                    isSelected = selectedPlan == 0,
                    onClick = { selectedPlan = 0 },
                    badge = "Limited-Time Offer"
                )

                // Annual Plan
                PlanItem(
                    title = "Annual Plan",
                    price = "$ 3.44",
                    period = "/Week",
                    subtitle = "$ 179/year",
                    footer = "Preferred by Professionals",
                    isSelected = selectedPlan == 1,
                    onClick = { selectedPlan = 1 }
                )
            }

            Spacer(modifier = Modifier.height(40.sdp))

            // Bottom Actions
            Column(
                modifier = Modifier.padding(horizontal = 16.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /* Continue */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.sdp)
                        .clip(RoundedCornerShape(28.sdp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFF8E2DE2), Color(0xFFFF2D81))
                            )
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Continue",
                        color = Color.White,
                        fontSize = 18.ssp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                TextButton(onClick = { /* Cancel anytime */ }) {
                    Text(
                        text = "Cancel anytime",
                        color = Color.Gray,
                        fontSize = 14.ssp
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(20.sdp))
        }
    }
}

@Composable
fun BenefitItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFFBA68C8), 
            modifier = Modifier.size(16.sdp)
        )
        Spacer(modifier = Modifier.width(12.sdp))
        Text(
            text = text,
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 14.ssp
        )
    }
}

@Composable
fun PlanItem(
    title: String,
    price: String,
    period: String,
    subtitle: String? = null,
    footer: String? = null,
    badge: String? = null,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (badge != null) {
            Box(
                modifier = Modifier
                    .padding(start = 12.sdp)
                    .background(Color(0xFFFF2D81), RoundedCornerShape(4.sdp))
                    .padding(horizontal = 8.sdp, vertical = 2.sdp)
            ) {
                Text(
                    text = badge,
                    color = Color.White,
                    fontSize = 10.ssp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.sdp))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.sdp))
                .background(if (isSelected) Color(0xFF2D1B3D) else Color(0xFF1C1C2E))
                .border(
                    width = if (isSelected) 2.sdp else 0.sdp,
                    color = if (isSelected) Color(0xFFFF2D81) else Color.Transparent,
                    shape = RoundedCornerShape(16.sdp)
                )
                .clickable { onClick() }
                .padding(20.sdp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 18.ssp,
                        fontWeight = FontWeight.Bold
                    )
                    if (subtitle != null) {
                        Text(
                            text = subtitle,
                            color = Color.Gray,
                            fontSize = 14.ssp
                        )
                    }
                }
                
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = price,
                        color = Color.White,
                        fontSize = 24.ssp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = period,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.ssp,
                        modifier = Modifier.padding(bottom = 4.sdp)
                    )
                }
            }
            
            if (footer != null) {
                Spacer(modifier = Modifier.height(12.sdp))
                Text(
                    text = footer,
                    color = Color(0xFFFFD54F),
                    fontSize = 12.ssp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}
