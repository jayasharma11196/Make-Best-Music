package com.example.make_best_music.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.HelpOutline
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun CreateMusicScreen() {
    var selectedTab by remember { mutableIntStateOf(0) } // 0: Simple, 1: Custom
    var isInstrumental by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141421))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.sdp),
            verticalArrangement = Arrangement.spacedBy(16.sdp)
        ) {
            item { Spacer(modifier = Modifier.height(20.sdp)) }

            // Header
            item {
                CreateMusicHeader()
            }

            // Tab Switcher
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.sdp)
                            .clip(RoundedCornerShape(20.sdp))
                            .background(Color(0xFF1E1E2E))
                            .padding(4.sdp)
                    ) {
                        TabItem(
                            text = "Simple",
                            isSelected = selectedTab == 0,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTab = 0 }
                        )
                        TabItem(
                            text = "Custom",
                            isSelected = selectedTab == 1,
                            modifier = Modifier.weight(1f),
                            onClick = { selectedTab = 1 }
                        )
                    }
                    Spacer(modifier = Modifier.width(12.sdp))
                    // v-Vox Dropdown
                    Box(
                        modifier = Modifier
                            .height(40.sdp)
                            .clip(RoundedCornerShape(20.sdp))
                            .background(Color(0xFF1E1E2E))
                            .padding(horizontal = 12.sdp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "v-Vox", color = Color.White, fontSize = 14.ssp)
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Gray)
                        }
                    }
                }
            }

            if (selectedTab == 0) {
                // Simple Tab Content
                item {
                    SimpleModeContent(isInstrumental, onInstrumentalChange = { isInstrumental = it })
                }
                item {
                    CreateButton()
                }
                item {
                    ForYouSection()
                }
            } else {
                // Custom Tab Content
                item {
                    CustomModeContent(isInstrumental, onInstrumentalChange = { isInstrumental = it })
                }
            }

            item { Spacer(modifier = Modifier.height(100.sdp)) }
        }
    }
}

@Composable
fun TabItem(text: String, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(16.sdp))
            .background(if (isSelected) Color(0xFF3A3A4A) else Color.Transparent)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Gray,
            fontSize = 14.ssp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun CreateMusicHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Create Music",
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
fun SimpleModeContent(isInstrumental: Boolean, onInstrumentalChange: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.sdp))
            .background(Color(0xFF1E1E2E))
            .padding(16.sdp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Lyrics Description", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Instrumental", color = Color.Gray, fontSize = 12.ssp)
                Spacer(modifier = Modifier.width(8.sdp))
                Switch(
                    checked = isInstrumental,
                    onCheckedChange = onInstrumentalChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFFFF2D81),
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.DarkGray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.sdp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.sdp)
                .clip(RoundedCornerShape(12.sdp))
                .background(Color(0xFF141421))
                .padding(12.sdp)
        ) {
            var descText by remember { mutableStateOf("") }
            if (descText.isEmpty()) {
                Text(
                    text = "Describe your lyrics\nFor example: Rap song about chasing dreams in the city.",
                    color = Color.Gray,
                    fontSize = 14.ssp
                )
            }
            BasicTextField(
                value = descText,
                onValueChange = { descText = it },
                textStyle = TextStyle(color = Color.White, fontSize = 14.ssp),
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier.fillMaxSize()
            )

            Icon(
                Icons.Default.Casino,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(24.sdp)
                    .background(Color.White.copy(alpha = 0.1f), RoundedCornerShape(4.sdp))
                    .padding(4.sdp)
            )
        }
    }
}

@Composable
fun CustomModeContent(isInstrumental: Boolean, onInstrumentalChange: (Boolean) -> Unit) {
    var isAdvancedExpanded by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(16.sdp)) {
        // Lyrics Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.sdp))
                .background(Color(0xFF1E1E2E))
                .padding(16.sdp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Lyrics", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.sdp))
                        .background(Color.White.copy(alpha = 0.1f))
                        .padding(horizontal = 8.sdp, vertical = 4.sdp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.sdp))
                        Text(text = "Reference", color = Color.Gray, fontSize = 12.ssp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.sdp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.sdp)
                    .clip(RoundedCornerShape(12.sdp))
                    .background(Color(0xFF141421))
                    .padding(12.sdp)
            ) {
                Text(text = "Enter lyrics with a full structure", color = Color.Gray, fontSize = 14.ssp)
                Icon(
                    Icons.Default.Fullscreen,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.align(Alignment.BottomEnd).size(20.sdp)
                )
            }

            Spacer(modifier = Modifier.height(12.sdp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = isInstrumental,
                    onCheckedChange = onInstrumentalChange,
                    colors = SwitchDefaults.colors(checkedTrackColor = Color(0xFFFF2D81))
                )
                Text(text = "Instrumental", color = Color.Gray, fontSize = 12.ssp, modifier = Modifier.padding(start = 8.sdp))
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.sdp))
                        .background(Color.White.copy(alpha = 0.1f))
                        .padding(horizontal = 8.sdp, vertical = 4.sdp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.AutoMirrored.Filled.Assignment, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.sdp))
                        Text(text = "Generate Lyrics", color = Color.Gray, fontSize = 12.ssp)
                    }
                }
            }
        }

        // Styles Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.sdp))
                .background(Color(0xFF1E1E2E))
                .padding(16.sdp)
        ) {
            Text(text = "Styles", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.sdp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.sdp)
                    .clip(RoundedCornerShape(12.sdp))
                    .background(Color(0xFF141421))
                    .padding(12.sdp)
            ) {
                Text(text = "Enter music styles or select from below.", color = Color.Gray, fontSize = 14.ssp)
                Icon(
                    Icons.Default.Casino,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.align(Alignment.BottomEnd).size(20.sdp)
                )
            }
            Spacer(modifier = Modifier.height(12.sdp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.sdp)) {
                StyleTag("# Genres >")
                StyleTag("# Moods >")
                StyleTag("# Voices >")
            }
            Spacer(modifier = Modifier.height(8.sdp))
            StyleTag("# Tempos >")
        }

        // Vocal Gender Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.sdp))
                .background(Color(0xFF1E1E2E))
                .padding(16.sdp)
        ) {
            Text(text = "Vocal Gender (Optional)", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.sdp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.sdp)) {
                GenderButton("Male", Icons.Default.Male, modifier = Modifier.weight(1f))
                GenderButton("Female", Icons.Default.Female, modifier = Modifier.weight(1f))
            }
        }

        // Advanced Options Section
        AdvancedOptionsSectionCreate(
            isExpanded = isAdvancedExpanded,
            onToggle = { isAdvancedExpanded = !isAdvancedExpanded }
        )

        CreateButton()
    }
}

@Composable
fun StyleTag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.sdp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(horizontal = 8.sdp, vertical = 6.sdp)
    ) {
        Text(text = text, color = Color.Gray, fontSize = 12.ssp)
    }
}

@Composable
fun GenderButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    Box(
        modifier = modifier
            .height(44.sdp)
            .clip(RoundedCornerShape(8.sdp))
            .background(Color(0xFF141421))
            .border(1.sdp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.sdp)),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.sdp))
            Spacer(modifier = Modifier.width(8.sdp))
            Text(text = text, color = Color.Gray, fontSize = 14.ssp)
        }
    }
}

@Composable
fun AdvancedOptionsSectionCreate(isExpanded: Boolean, onToggle: () -> Unit) {
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
            Text(text = "Advanced Options", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
            Icon(
                if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Color.Gray
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 16.sdp), verticalArrangement = Arrangement.spacedBy(16.sdp)) {
                // Song Title
                Column {
                    Text(text = "Song Title", color = Color.White, fontSize = 14.ssp)
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
                        Text(text = "Enter the song title", color = Color.DarkGray, fontSize = 14.ssp)
                    }
                }

                // Excluded Styles
                Column {
                    Text(text = "Excluded Styles", color = Color.White, fontSize = 14.ssp)
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
                        Text(text = "Enter Excluded Styles Here", color = Color.DarkGray, fontSize = 14.ssp)
                    }
                }

                // Weirdness Slider
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Weirdness", color = Color.White, fontSize = 14.ssp)
                        Spacer(modifier = Modifier.width(8.sdp))
                        Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.sdp))
                    }
                    Slider(value = 0.5f, onValueChange = {}, colors = SliderDefaults.colors(thumbColor = Color.White, activeTrackColor = Color(0xFFFF2D81)))
                }

                // Style Influence Slider
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Style Influence", color = Color.White, fontSize = 14.ssp)
                        Spacer(modifier = Modifier.width(8.sdp))
                        Icon(Icons.AutoMirrored.Filled.HelpOutline, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.sdp))
                    }
                    Slider(value = 0.5f, onValueChange = {}, colors = SliderDefaults.colors(thumbColor = Color.White, activeTrackColor = Color(0xFFFF2D81)))
                }
            }
        }
    }
}

@Composable
fun CreateButton() {
    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.sdp)
            .clip(RoundedCornerShape(25.sdp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF8E2DE2), Color(0xFFFF2D81))
                )
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.sdp))
            Spacer(modifier = Modifier.width(8.sdp))
            Text(text = "Create", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ForYouSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.sdp))
            .background(Color(0xFF1E1E2E))
            .padding(16.sdp)
    ) {
        Text(text = "For You", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.sdp))
        
        // Tags
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.sdp)) {
            items(listOf("Pop", "Urban/R&B", "Funk", "Country")) { tag ->
                val isSelected = tag == "Pop"
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.sdp))
                        .background(if (isSelected) Color(0xFFFF2D81) else Color(0xFF3A3A4A))
                        .padding(horizontal = 12.sdp, vertical = 6.sdp)
                ) {
                    Text(text = tag, color = Color.White, fontSize = 12.ssp)
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.sdp))
        
        // Song Items
        SongItem("Empty Window", "https://i.pravatar.cc/150?u=10")
        Spacer(modifier = Modifier.height(12.sdp))
        SongItem("Beach Bound", "https://i.pravatar.cc/150?u=11")
    }
}

@Composable
fun SongItem(title: String, imageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.sdp))
            .background(Color(0xFF141421))
            .padding(8.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        coil.compose.AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.size(50.sdp).clip(RoundedCornerShape(8.sdp)),
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.iv_music)
        )
        Spacer(modifier = Modifier.width(12.sdp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = Color.White, fontSize = 14.ssp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.sdp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.sdp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(horizontal = 8.sdp, vertical = 2.sdp)
            ) {
                Text(text = "Create Similar", color = Color.Gray, fontSize = 10.ssp)
            }
        }
        Icon(
            Icons.Default.PlayArrow,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.sdp).clip(CircleShape).background(Color.White.copy(alpha = 0.2f)).padding(4.sdp)
        )
    }
}
