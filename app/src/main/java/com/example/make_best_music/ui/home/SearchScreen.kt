package com.example.make_best_music.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    viewModel: CommunityViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val filteredPosts by viewModel.filteredPosts.collectAsState(initial = emptyList())
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Music", "Creator")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF141421))
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.sdp, vertical = 8.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            
            // Custom Tabs
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                tabs.forEachIndexed { index, title ->
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.sdp)
                            .clickable { selectedTabIndex = index },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray,
                            fontSize = 16.ssp,
                            fontWeight = FontWeight.Bold
                        )
                        if (selectedTabIndex == index) {
                            Spacer(modifier = Modifier.height(4.sdp))
                            Box(
                                modifier = Modifier
                                    .width(20.sdp)
                                    .height(2.sdp)
                                    .background(Color(0xFFFF2D81))
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(48.sdp)) // To balance the back button
        }

        // Search Bar
        TextField(
            value = uiState.searchQuery,
            onValueChange = { viewModel.setSearchQuery(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.sdp, vertical = 8.sdp)
                .height(50.sdp)
                .clip(RoundedCornerShape(25.sdp)),
            placeholder = {
                Text(
                    text = if (selectedTabIndex == 0) "Search music title..." else "Search creators...",
                    color = Color.Gray,
                    fontSize = 14.ssp
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF252535),
                unfocusedContainerColor = Color(0xFF252535),
                disabledContainerColor = Color(0xFF252535),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            singleLine = true
        )

        if (selectedTabIndex == 0) {
            // Music Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.sdp),
                horizontalArrangement = Arrangement.spacedBy(12.sdp),
                verticalArrangement = Arrangement.spacedBy(16.sdp)
            ) {
                items(filteredPosts) { post ->
                    PostItem(post)
                }
            }
        } else {
            // Creators List
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 40.sdp)
                ) {
                    items(filteredPosts) { post ->
                        CreatorListItem(post)
                    }
                    item {
                        Text(
                            text = "Only creators with posted works are searchable.",
                            color = Color.Gray,
                            fontSize = 10.ssp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.sdp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CreatorListItem(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.sdp, vertical = 12.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(48.sdp)
                .clip(CircleShape)
                .background(Color(0xFF5E5CE6)), // Using a purple color for avatar background
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = post.userName.take(1).uppercase(),
                color = Color.White,
                fontSize = 20.ssp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.width(12.sdp))
        
        // Info
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = post.userName,
                color = Color.White,
                fontSize = 14.ssp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "No description yet",
                color = Color.Gray,
                fontSize = 11.ssp
            )
        }
        
        // Follow Button
        IconButton(
            onClick = { /* Follow */ },
            modifier = Modifier
                .size(36.sdp)
                .border(1.sdp, Color.White, CircleShape)
        ) {
            Icon(
                Icons.Default.PersonAdd,
                contentDescription = "Follow",
                tint = Color.White,
                modifier = Modifier.size(18.sdp)
            )
        }
    }
}
