package com.example.make_best_music.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import com.example.make_best_music.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp
import kotlinx.coroutines.launch

@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = viewModel(),
    onUploadClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(horizontal = 16.sdp)
    ) {
        Spacer(modifier = Modifier.height(20.sdp))

        // Header
        LibraryHeader(
            selectedTab = pagerState.currentPage,
            onUploadClick = onUploadClick
        )

        Spacer(modifier = Modifier.height(20.sdp))

        // Custom Tab Layout
        LibraryTabs(
            selectedTab = pagerState.currentPage,
            onTabSelected = { index ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )

        Spacer(modifier = Modifier.height(20.sdp))

        // ViewPager Content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> MusicTabContent(hasMusic = uiState.hasMusic)
                1 -> FolderTabContent(hasFolder = uiState.hasFolder)
                2 -> CoverTabContent(hasCover = uiState.hasCover)
            }
        }
    }
}

@Composable
fun LibraryHeader(selectedTab: Int, onUploadClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Library",
            color = Color.White,
            fontSize = 22.ssp,
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
                            .border(1.sdp, Color(0xFF121212), CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.sdp))

            // Cloud Upload or Search Icon
            if (selectedTab == 2) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.sdp)
                )
            } else {
                Surface(
                    modifier = Modifier.size(36.sdp).clickable { onUploadClick() },
                    shape = RoundedCornerShape(10.sdp),
                    color = Color(0xFF1E1E1E)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(20.sdp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LibraryTabs(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val tabs = listOf("Music", "Folder", "Cover")
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.sdp)
            .clip(RoundedCornerShape(20.sdp))
            .background(Color(0xFF1E1E2E))
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            tabs.forEachIndexed { index, title ->
                val isSelected = selectedTab == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.sdp))
                        .background(if (isSelected) Color(0xFF3F3F4F) else Color.Transparent)
                        .clickable { onTabSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        color = if (isSelected) Color.White else Color.Gray,
                        fontSize = 14.ssp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Composable
fun MusicTabContent(hasMusic: Boolean) {
    if (!hasMusic) {
        EmptyLibraryState(
            painter = painterResource(id = R.drawable.iv_music),
            mainText = "There's no music yet.",
            subText = "Hurry up and create some music",
            buttonText = "Create Music",
            tint = Color(0xFFFF2D81),
            isImagePainter = true
        )
    } else {
        // Music list would go here
    }
}

@Composable
fun FolderTabContent(hasFolder: Boolean) {
    if (!hasFolder) {
        EmptyLibraryState(
            painter = painterResource(id = R.drawable.folder),
            mainText = "There is no folder yet.",
            subText = "Hurry up and create a folder",
            buttonText = "Create Folder",
            tint = Color(0xFFFFA726),
            isImagePainter = true
        )
    } else {
        // Folder list would go here
    }
}

@Composable
fun CoverTabContent(hasCover: Boolean) {
    if (!hasCover) {
        EmptyLibraryState(
            painter = painterResource(id = R.drawable.iv_music),
            mainText = "AI Voice Replacement for Seamless Song Covers",
            subText = "",
            buttonText = "Cover Now",
            tint = Color(0xFFFF2D81),
            isImagePainter = true
        )
    } else {
        // Cover list would go here
    }
}

@Composable
fun EmptyLibraryState(
    painter: Painter,
    mainText: String,
    subText: String,
    buttonText: String,
    tint: Color,
    isImagePainter: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Glowing Icon
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(120.sdp)
        ) {
            if (isImagePainter) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(60.sdp)
                )
            } else {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    tint = tint,
                    modifier = Modifier.size(60.sdp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.sdp))

        Text(
            text = mainText,
            color = Color.White,
            fontSize = 14.ssp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.sdp)
        )
        if (subText.isNotEmpty()) {
            Text(
                text = subText,
                color = Color.Gray,
                fontSize = 12.ssp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(30.sdp))

        Button(
            onClick = { /* Handle Create */ },
            modifier = Modifier
                .width(180.sdp)
                .height(44.sdp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(22.sdp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFFF2D81), Color(0xFFFF5F6D))
                        ),
                        shape = RoundedCornerShape(22.sdp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buttonText,
                    color = Color.White,
                    fontSize = 14.ssp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(100.sdp))
    }
}
