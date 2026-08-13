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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun ProfileScreen(
    onNotificationsClick: () -> Unit,
    onPricingClick: () -> Unit,
    onFAQClick: () -> Unit,
    onMoreSettingsClick: () -> Unit
) {
    val backgroundColor = Color(0xFF141421)
    val surfaceColor = Color(0xFF1C1C2E)
    val dividerColor = Color(0xFF2B2B40)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(top = 40.sdp, bottom = 20.sdp, start = 20.sdp, end = 20.sdp)
    ) {
        // User Profile Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Avatar Placeholder (You can replace this with Coil/Glide Image)
            Box(
                modifier = Modifier
                    .size(70.sdp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .border(2.sdp, Brush.linearGradient(listOf(Color.Cyan, Color.Magenta)), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize().padding(10.sdp)
                )
            }
            
            Spacer(modifier = Modifier.width(16.sdp))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Karan Upadhyay",
                    color = Color.White,
                    fontSize = 18.ssp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.sdp))

                Box(
                    modifier = Modifier
                        .background(surfaceColor, RoundedCornerShape(12.sdp))
                        .padding(horizontal = 12.sdp, vertical = 4.sdp)
                ) {
                    Text(
                        text = "Credits: 3",
                        color = Color.LightGray,
                        fontSize = 12.ssp
                    )
                }
            }
            
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Go to Profile",
                tint = Color.White,
                modifier = Modifier.size(24.sdp)
            )
        }

        Spacer(modifier = Modifier.height(24.sdp))

        // Premium Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.sdp) // Fixed height to maintain aspect ratio of background
                .clip(RoundedCornerShape(16.sdp))
                .clickable { onPricingClick() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.card_background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            // Help Icon in top right
            Icon(
                imageVector = Icons.Default.HelpOutline,
                contentDescription = "Help",
                tint = Color.White.copy(alpha = 0.4f),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.sdp)
                    .size(22.sdp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(top = 10.sdp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.crown),
                    contentDescription = "Crown",
                    modifier = Modifier.size(60.sdp)
                )
                
                Spacer(modifier = Modifier.height(16.sdp))
                
                Text(
                    text = "Unlock All Features & Benefits",
                    color = Color.White,
                    fontSize = 15.ssp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.sdp))

        // Options List
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(surfaceColor, RoundedCornerShape(16.sdp))
        ) {
            ProfileOptionItem(
                icon = Icons.Default.Notifications,
                iconTint = Color(0xFF64B5F6), // Light blue
                title = "Notifications",
                showDivider = true,
                onClick = onNotificationsClick
            )

            ProfileOptionItem(
                icon = Icons.Default.Explore, // Diamond-like icon
                iconTint = Color(0xFFBA68C8), // Purple
                title = "Getting Started Guide",
                showDivider = true,
                onClick = { /* Handle click */ }
            )

            ProfileOptionItem(
                icon = Icons.Default.LocalOffer, // Tag icon
                iconTint = Color(0xFFFFE082), // Light yellow
                title = "Pricing",
                showDivider = true,
                onClick = onPricingClick
            )

            ProfileOptionItem(
                icon = Icons.Default.Help,
                iconTint = Color(0xFF81C784), // Light green
                title = "FAQs",
                showDivider = true,
                onClick = onFAQClick
            )

            ProfileOptionItem(
                icon = Icons.Default.ChatBubbleOutline,
                iconTint = Color(0xFFFFD54F), // Yellow/gold
                title = "Support",
                showDivider = true,
                onClick = { /* Handle click */ }
            )

            ProfileOptionItem(
                icon = Icons.Default.Settings,
                iconTint = Color(0xFF9575CD), // Deep purple
                title = "More Settings",
                showDivider = true,
                onClick = onMoreSettingsClick
            )

            ProfileOptionItem(
                icon = Icons.Default.Layers,
                iconTint = Color(0xFFCE93D8), // Pinkish
                title = "Version",
                trailingText = "v1.7.0",
                showDivider = false,
                onClick = { /* Handle click */ }
            )
        }
    }
}

@Composable
fun ProfileOptionItem(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    trailingText: String? = null,
    showDivider: Boolean,
    onClick: () -> Unit
) {
    val dividerColor = Color(0xFF2B2B40)
    
    Column(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.sdp, vertical = 16.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(22.sdp)
            )
            
            Spacer(modifier = Modifier.width(16.sdp))
            
            Text(
                text = title,
                color = Color.White,
                fontSize = 14.ssp,
                modifier = Modifier.weight(1f)
            )
            
            if (trailingText != null) {
                Text(
                    text = trailingText,
                    color = Color.Gray,
                    fontSize = 12.ssp
                )
            } else {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Navigate",
                    tint = Color.Gray,
                    modifier = Modifier.size(20.sdp)
                )
            }
        }
        
        if (showDivider) {
            Divider(
                color = dividerColor,
                thickness = 1.sdp,
                modifier = Modifier.padding(horizontal = 16.sdp)
            )
        }
    }
}
