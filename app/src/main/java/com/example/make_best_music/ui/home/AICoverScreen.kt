package com.example.make_best_music.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun AICoverScreen() {
    var selectedVoiceId by remember { mutableIntStateOf(1) }
    var isAdvancedOptionsExpanded by remember { mutableStateOf(false) }
    var selectedTimbreTab by remember { mutableIntStateOf(1) } // 0: My Voice, 1: Public Voice

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141421))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.sdp),
            verticalArrangement = Arrangement.spacedBy(20.sdp)
        ) {
            item { Spacer(modifier = Modifier.height(20.sdp)) }

            item { AICoverHeader() }

            item { AudioSelectionSection() }

            item {
                TimbreSection(
                    selectedTab = selectedTimbreTab,
                    onTabSelected = { selectedTimbreTab = it }
                )
            }

            item {
                if (selectedTimbreTab == 1) {
                    VoiceGrid(
                        selectedVoiceId = selectedVoiceId,
                        onVoiceSelected = { selectedVoiceId = it }
                    )
                } else {
                    MyVoiceSection()
                }
            }

            item {
                AdvancedOptionsSection(
                    isExpanded = isAdvancedOptionsExpanded,
                    onToggle = { isAdvancedOptionsExpanded = !isAdvancedOptionsExpanded }
                )
            }

            item {
                CoverNowButton()
                Spacer(modifier = Modifier.height(100.sdp)) // Padding for bottom nav
            }
        }
    }
}

@Composable
fun AICoverHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "AI Cover",
            color = Color.White,
            fontSize = 24.ssp,
            fontWeight = FontWeight.Bold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Checklist Icon
            Surface(
                modifier = Modifier.size(36.sdp),
                shape = RoundedCornerShape(10.sdp),
                color = Color(0xFF1E1E1E)
            ) {
                Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.leader_board),
                    contentDescription = null,
                    modifier = Modifier.size(20.sdp)
                )
                // Red dot
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 2.sdp, y = (-2).sdp)
                        .size(8.sdp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .border(1.sdp, Color(0xFF141421), CircleShape)
                )
            }
            }
            Spacer(modifier = Modifier.width(12.sdp))
            Box(
                modifier = Modifier
                    .height(30.sdp)
                    .clip(RoundedCornerShape(15.sdp))
                    .background(Color(0xFFFF2D81).copy(alpha = 0.2f))
                    .padding(horizontal = 10.sdp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color(0xFFFF2D81),
                        modifier = Modifier.size(16.sdp)
                    )
                    Spacer(modifier = Modifier.width(4.sdp))
                    Text(text = "3", color = Color(0xFFFF2D81), fontWeight = FontWeight.Bold, fontSize = 14.ssp)
                }
            }
        }
    }
}

@Composable
fun AudioSelectionSection() {
    Column {
        Text(
            text = "Audio file to be covered",
            color = Color.White,
            fontSize = 16.ssp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.sdp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.sdp)) {
            AudioCard(
                title = "Device",
                modifier = Modifier.weight(1f),
                icon = Icons.Default.PhoneIphone
            )
            AudioCard(
                title = "library",
                modifier = Modifier.weight(1f),
                icon = Icons.Default.Folder
            )
        }
    }
}

@Composable
fun AudioCard(title: String, modifier: Modifier = Modifier, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Box(
        modifier = modifier
            .height(120.sdp)
            .clip(RoundedCornerShape(16.sdp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF8E2DE2).copy(alpha = 0.6f), Color(0xFF4A00E0).copy(alpha = 0.6f))
                )
            )
    ) {
        // Vinyl Record Placeholder / Icon
        Icon(
            icon,
            contentDescription = null,
            tint = Color.Black.copy(alpha = 0.3f),
            modifier = Modifier
                .size(100.sdp)
                .align(Alignment.TopCenter)
                .offset(y = (-20).sdp)
        )
        
        // Content
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .clip(RoundedCornerShape(bottomStart = 16.sdp, bottomEnd = 16.sdp))
                .background(Color.White.copy(alpha = 0.1f))
                .border(1.sdp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(bottomStart = 16.sdp, bottomEnd = 16.sdp))
                .padding(12.sdp)
        ) {
            Column {
                Text(text = "Select from", color = Color.White.copy(alpha = 0.7f), fontSize = 10.ssp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = title, color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(24.sdp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.sdp))
                    }
                }
            }
        }
    }
}

@Composable
fun TimbreSection(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Column {
        Text(
            text = "Cover Audio Timbre",
            color = Color.White,
            fontSize = 16.ssp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(12.sdp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.sdp))
                    .background(Color(0xFF1E1E2E))
                    .padding(4.sdp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.sdp))
                        .background(if (selectedTab == 0) Color.Transparent else Color.Transparent)
                        .border(
                            width = if (selectedTab == 0) 1.sdp else 0.sdp,
                            color = if (selectedTab == 0) Color(0xFFFF2D81) else Color.Transparent,
                            shape = RoundedCornerShape(16.sdp)
                        )
                        .clickable { onTabSelected(0) }
                        .padding(horizontal = 16.sdp, vertical = 8.sdp)
                ) {
                    Text(
                        text = "My Voice",
                        color = if (selectedTab == 0) Color(0xFFFF2D81) else Color.Gray,
                        fontSize = 14.ssp,
                        fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.sdp))
                        .border(
                            width = if (selectedTab == 1) 1.sdp else 0.sdp,
                            color = if (selectedTab == 1) Color(0xFFFF2D81) else Color.Transparent,
                            shape = RoundedCornerShape(16.sdp)
                        )
                        .clickable { onTabSelected(1) }
                        .padding(horizontal = 16.sdp, vertical = 8.sdp)
                ) {
                    Text(
                        text = "Public Voice",
                        color = if (selectedTab == 1) Color(0xFFFF2D81) else Color.Gray,
                        fontSize = 14.ssp,
                        fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "More", color = Color.Gray, fontSize = 14.ssp)
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
            }
        }
    }
}

@Composable
fun MyVoiceSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.sdp))
            .background(Color(0xFF1E1E2E))
            .padding(16.sdp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .width(100.sdp)
                    .height(140.sdp)
                    .clip(RoundedCornerShape(12.sdp))
                    .background(Color(0xFF141421))
                    .clickable { /* Handle Clone Voice */ },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(40.sdp)
                            .border(1.sdp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(8.sdp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(24.sdp)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.sdp))
                    Text(
                        text = "Clone Voice",
                        color = Color.White,
                        fontSize = 12.ssp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.sdp))
        }
    }
}

data class Voice(val id: Int, val name: String, val gender: String, val image: String)

@Composable
fun VoiceGrid(selectedVoiceId: Int, onVoiceSelected: (Int) -> Unit) {
    val voices = listOf(
        Voice(1, "Ariana Gra...", "female", "https://i.pravatar.cc/150?u=1"),
        Voice(2, "Justin Bie...", "male", "https://i.pravatar.cc/150?u=2"),
        Voice(3, "Marco Me...", "male", "https://i.pravatar.cc/150?u=3"),
        Voice(4, "Zucchero", "male", "https://i.pravatar.cc/150?u=4"),
        Voice(5, "Toto Cutu...", "male", "https://i.pravatar.cc/150?u=5"),
        Voice(6, "Laura Pau...", "female", "https://i.pravatar.cc/150?u=6")
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.sdp))
            .background(Color(0xFF1E1E2E))
            .padding(12.sdp)
    ) {
        Column {
            val rows = voices.chunked(3)
            rows.forEach { rowVoices ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.sdp)) {
                    rowVoices.forEach { voice ->
                        VoiceItem(
                            voice = voice,
                            isSelected = voice.id == selectedVoiceId,
                            modifier = Modifier.weight(1f),
                            onClick = { onVoiceSelected(voice.id) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.sdp))
            }
        }
    }
}

@Composable
fun VoiceItem(voice: Voice, isSelected: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.sdp))
                .border(
                    width = if (isSelected) 2.sdp else 0.sdp,
                    color = if (isSelected) Color(0xFFFF2D81) else Color.Transparent,
                    shape = RoundedCornerShape(12.sdp)
                )
        ) {
            AsyncImage(
                model = voice.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().background(Color.Gray)
            )
            
            // Play Button Overlay
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.sdp)
                    .size(20.sdp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFFFF2D81), modifier = Modifier.size(14.sdp))
            }
        }
        Spacer(modifier = Modifier.height(4.sdp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = voice.name,
                color = if (isSelected) Color(0xFFFF2D81) else Color.White,
                fontSize = 10.ssp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.width(2.sdp))
            Icon(
                imageVector = if (voice.gender == "female") Icons.Default.Female else Icons.Default.Male,
                contentDescription = null,
                tint = if (voice.gender == "female") Color(0xFFFF69B4) else Color(0xFF6495ED),
                modifier = Modifier.size(12.sdp)
            )
        }
    }
}

@Composable
fun AdvancedOptionsSection(isExpanded: Boolean, onToggle: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.sdp))
            .background(Color(0xFF1E1E2E))
            .padding(16.sdp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { onToggle() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Advanced Options", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.SemiBold)
            Icon(
                if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 16.sdp)) {
                Text(text = "AI Key Shifting", color = Color.Gray, fontSize = 12.ssp)
                Spacer(modifier = Modifier.height(8.sdp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.sdp)
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color(0xFF141421))
                        .padding(horizontal = 12.sdp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "No Shifting", color = Color.White, fontSize = 14.ssp)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(16.sdp))

                Text(text = "Cover Audio Title", color = Color.Gray, fontSize = 12.ssp)
                Spacer(modifier = Modifier.height(8.sdp))
                var titleText by remember { mutableStateOf("") }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.sdp)
                        .clip(RoundedCornerShape(8.sdp))
                        .background(Color(0xFF141421))
                        .padding(horizontal = 12.sdp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (titleText.isEmpty()) {
                        Text(text = "Cover Audio Title", color = Color.DarkGray, fontSize = 14.ssp)
                    }
                    BasicTextField(
                        value = titleText,
                        onValueChange = { titleText = it },
                        textStyle = androidx.compose.ui.text.TextStyle(color = Color.White, fontSize = 14.ssp),
                        cursorBrush = SolidColor(Color.White),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun CoverNowButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.sdp)
            .clip(RoundedCornerShape(28.sdp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF8E2DE2), Color(0xFFFF2D81))
                )
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Text(text = "Cover Now", color = Color.White, fontSize = 18.ssp, fontWeight = FontWeight.Bold)
    }
}
