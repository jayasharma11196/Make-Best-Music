package com.example.make_best_music.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.lazy.LazyColumn
import com.example.make_best_music.R

@Composable
fun CommunityScreen(viewModel: CommunityViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    var showPostDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF141421))) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.sdp, end = 16.sdp, top = 20.sdp, bottom = 100.sdp)
        ) {
            item { 
                HeaderSection()
                Spacer(modifier = Modifier.height(16.sdp))
            }
            
            item { 
                CategoryList(uiState.categories)
                Spacer(modifier = Modifier.height(16.sdp))
            }
            
            item { 
                TabSection(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
                Spacer(modifier = Modifier.height(16.sdp))
            }
            
            if (selectedTab == 0) {
                // For You: Horizontal rows
                item {
                    Text("Trending Now", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.sdp))
                    PostRow(uiState.posts)
                    Spacer(modifier = Modifier.height(20.sdp))
                }
                
                item {
                    Text("Recommended For You", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.sdp))
                    PostRow(uiState.posts.reversed())
                    Spacer(modifier = Modifier.height(20.sdp))
                }
                
                item {
                    Text("New Releases", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(10.sdp))
                    PostRow(uiState.posts)
                }
            } else if (selectedTab == 1) {
                // New: Vertical Grid (2 columns)
                val chunkedPosts = uiState.posts.chunked(2)
                items(chunkedPosts) { rowPosts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.sdp)
                    ) {
                        for (post in rowPosts) {
                            Box(modifier = Modifier.weight(1f)) {
                                PostItem(post)
                            }
                        }
                        if (rowPosts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.sdp))
                }
            } else {
                // Empty state for other tabs
                item {
                    EmptyState()
                }
            }
        }

        // Post FAB
        FloatingActionButton(
            onClick = { showPostDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.sdp, end = 16.sdp)
                .size(60.sdp),
            containerColor = Color(0xFFFF2D81),
            shape = CircleShape
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Box(
                    modifier = Modifier.size(22.sdp).border(1.sdp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.sdp))
                }
                Spacer(modifier = Modifier.height(2.sdp))
                Text("Post", color = Color.White, fontSize = 11.ssp, fontWeight = FontWeight.Bold)
            }
        }

        if (showPostDialog) {
            PostMusicDialog(onDismiss = { showPostDialog = false })
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Community",
                color = Color.White,
                fontSize = 22.ssp,
                fontWeight = FontWeight.Bold
            )
            Row {
                Text(
                    text = "Post music ",
                    color = Color(0xFFFF2D81),
                    fontSize = 12.ssp
                )
                Text(
                    text = "& earn rewards.",
                    color = Color.Gray,
                    fontSize = 12.ssp
                )
            }
        }




        
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
    }
}

@Composable
fun CategoryList(categories: List<Category>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.sdp)
    ) {
        items(categories) { category ->
            val isCustomBg = category.name == "Pop" || category.name == "Rock" || category.name == "Electronic"
            Box(
                modifier = Modifier
                    .width(100.sdp)
                    .height(80.sdp)
                    .clip(RoundedCornerShape(12.sdp))
                    .then(
                        if (isCustomBg) {
                            Modifier
                        } else {
                            Modifier.background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(category.color).copy(alpha = 0.5f),
                                        Color(category.color)
                                    )
                                )
                            )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                when (category.name) {
                    "Pop" -> {
                        Image(
                            painter = painterResource(id = R.drawable.pop_bg),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    "Rock" -> {
                        Image(
                            painter = painterResource(id = R.drawable.rock_bg),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    "Electronic" -> {
                        Image(
                            painter = painterResource(id = R.drawable.electronic_bg),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Text(
                    text = category.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.ssp
                )
            }
        }
        item {
            // "Next" arrow button
            Box(
                modifier = Modifier
                    .width(40.sdp)
                    .height(80.sdp)
                    .clip(RoundedCornerShape(12.sdp))
                    .background(Color(0xFF1E1E1E)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun TabSection(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("For You", "New", "Following", "Likes", "Elites")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(15.sdp)
        ) {
            items(tabs.size) { index ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = tabs[index],
                        color = if (selectedTab == index) Color.White else Color.Gray,
                        fontSize = 14.ssp,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.clickable { onTabSelected(index) }
                    )
                    if (selectedTab == index) {
                        Spacer(modifier = Modifier.height(4.sdp))
                        Box(modifier = Modifier.width(20.sdp).height(2.sdp).background(Color(0xFFFF2D81)))
                    }
                }
            }
        }
        
        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.sdp))
    }
}

@Composable
fun PostRow(posts: List<Post>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.sdp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(posts) { post ->
            Box(modifier = Modifier.width(160.sdp)) { 
                PostItem(post)
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.sdp)
                .clip(RoundedCornerShape(12.sdp))
                .background(Color(0xFF1E1E1E))
        ) {
            // Image
            AsyncImage(
                model = post.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().background(Color.DarkGray)
            )
            
            // Labels
            Row(modifier = Modifier.padding(8.sdp).align(Alignment.TopStart), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.clip(RoundedCornerShape(12.sdp)).background(Color.Black.copy(alpha = 0.6f)).padding(horizontal = 6.sdp, vertical = 2.sdp)) {
                    Text(text = post.duration, color = Color.White, fontSize = 8.ssp)
                }
                Spacer(modifier = Modifier.width(4.sdp))
                Box(modifier = Modifier.clip(RoundedCornerShape(12.sdp)).background(Color.Black.copy(alpha = 0.6f)).padding(horizontal = 6.sdp, vertical = 2.sdp)) {
                    Text(text = "v-Vox", color = Color(0xFFFF2D81), fontSize = 8.ssp, fontWeight = FontWeight.Bold)
                }
            }
            
            // Play icon (Top Right)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.sdp)
                    .size(28.sdp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.sdp)
                )
            }
            
            // Bottom labels
            Column(modifier = Modifier.align(Alignment.BottomStart).padding(8.sdp)) {
                Text(text = post.title, color = Color.White, fontSize = 10.ssp, fontWeight = FontWeight.Bold, maxLines = 1)
                Spacer(modifier = Modifier.height(4.sdp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.sdp)) {
                    post.tags.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.sdp))
                                .background(Color.Black.copy(alpha = 0.4f))
                                .border(1.sdp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(12.sdp))
                                .padding(horizontal = 6.sdp, vertical = 2.sdp)
                        ) {
                            Text(text = tag, color = Color.White.copy(alpha = 0.8f), fontSize = 7.ssp)
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.sdp))
        
        // User Info
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = "https://i.pravatar.cc/150?u=${post.id}", // Dummy avatar
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(20.sdp).clip(CircleShape).background(Color.Gray)
            )
            Spacer(modifier = Modifier.width(6.sdp))
            Text(text = post.userName, color = Color.White, fontSize = 10.ssp, maxLines = 1)
        }
        
        Spacer(modifier = Modifier.height(4.sdp))
        
        // Stats
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.sdp)) {
            StatItem(icon = Icons.Outlined.PlayArrow, value = post.views)
            StatItem(icon = Icons.Outlined.ThumbUp, value = post.likes)
            StatItem(icon = Icons.Outlined.ChatBubbleOutline, value = post.comments)
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 40.sdp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = com.example.make_best_music.R.drawable.no_record),
            contentDescription = null,
            modifier = Modifier.size(150.sdp)
        )
        Spacer(modifier = Modifier.height(16.sdp))
        Text("No tracks yet", color = Color.White, fontSize = 14.ssp)
        Spacer(modifier = Modifier.height(30.sdp))
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2D81)),
            shape = RoundedCornerShape(24.sdp),
            modifier = Modifier.height(48.sdp).padding(horizontal = 30.sdp)
        ) {
            Text("View Recommended", color = Color.White, fontSize = 14.ssp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun StatItem(icon: androidx.compose.ui.graphics.vector.ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(12.sdp))
        Spacer(modifier = Modifier.width(2.sdp))
        Text(text = value, color = Color.Gray, fontSize = 9.ssp)
    }
}

@Composable
fun PostMusicDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.sdp)
                .clip(RoundedCornerShape(24.sdp))
                .background(Color(0xFF252839)) // Dark navy blue background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.sdp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title and Close Icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Post Music",
                        color = Color.White,
                        fontSize = 18.ssp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = onDismiss, modifier = Modifier.size(24.sdp)) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(24.sdp))

                // Heading
                Text(
                    text = "Let the world hear your creation!",
                    color = Color.White,
                    fontSize = 18.ssp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.sdp))
                
                // Subheading
                Text(
                    text = "Earn tips and remix rewards as your music grows.",
                    color = Color.Gray,
                    fontSize = 12.ssp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.sdp))

                // Select Music Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.sdp)
                        .clip(RoundedCornerShape(16.sdp))
                        .background(Color(0xFF1A1D2D))
                        .clickable { /* Select music */ },
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.sdp))
                        Spacer(modifier = Modifier.width(8.sdp))
                        Text(text = "Select Music", color = Color.White, fontSize = 16.ssp, fontWeight = FontWeight.Medium)
                    }
                }

                Spacer(modifier = Modifier.height(16.sdp))

                // Tag section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tag", color = Color.White, fontSize = 14.ssp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(8.sdp))
                    Text(text = "Auto-detect based on song", color = Color.Gray, fontSize = 12.ssp)
                }

                Spacer(modifier = Modifier.height(30.sdp))

                // Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.sdp)
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.sdp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D4050)),
                        shape = RoundedCornerShape(24.sdp)
                    ) {
                        Text("Cancel", color = Color.White, fontSize = 14.ssp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { /* Post */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.sdp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4A4D5D), // Looks disabled in the image
                            contentColor = Color.White.copy(alpha = 0.5f)
                        ),
                        shape = RoundedCornerShape(24.sdp)
                    ) {
                        Text("Post", fontSize = 14.ssp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
