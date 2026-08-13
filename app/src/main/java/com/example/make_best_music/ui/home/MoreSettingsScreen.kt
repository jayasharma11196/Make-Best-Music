package com.example.make_best_music.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreSettingsScreen(
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onCreditDetailsClick: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        DeleteAccountDialog(
            onDismiss = { showDeleteDialog = false },
            onConfirm = {
                showDeleteDialog = false
                // Handle account deletion logic here
            }
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "More Settings",
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
        containerColor = Color(0xFF141421),
        bottomBar = {
            Button(
                onClick = onLogoutClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.sdp)
                    .height(50.sdp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2D81)),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(25.sdp)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    fontSize = 16.ssp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 20.sdp)
        ) {
            item {
                SettingsItem(
                    icon = Icons.AutoMirrored.Filled.Notes,
                    title = "Credit Details",
                    onClick = onCreditDetailsClick
                )
            }
            item {
                SettingsItem(
                    icon = Icons.Default.PrivacyTip,
                    title = "Privacy Policy",
                    onClick = { /* Handle Privacy Policy */ }
                )
            }
            item {
                SettingsItem(
                    icon = Icons.AutoMirrored.Filled.Assignment,
                    title = "Terms",
                    onClick = { /* Handle Terms */ }
                )
            }
            item {
                SettingsItem(
                    icon = Icons.Default.PersonRemove,
                    title = "Delete Account",
                    onClick = { showDeleteDialog = true }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteAccountDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(24.sdp))
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF2B2B40))
                .padding(24.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Once an account is deleted, it cannot\nbe restored.",
                color = Color.White,
                fontSize = 16.ssp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 22.ssp
            )

            Spacer(modifier = Modifier.height(30.sdp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.sdp)
            ) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.sdp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A3A4A)),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(22.sdp)
                ) {
                    Text(text = "Cancel", color = Color.White, fontSize = 16.ssp)
                }

                Button(
                    onClick = onConfirm,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.sdp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2D81)),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(22.sdp)
                ) {
                    Text(text = "Delete", color = Color.White, fontSize = 16.ssp)
                }
            }
        }
    }
}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.sdp, vertical = 20.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.size(24.sdp)
        )
        Spacer(modifier = Modifier.width(16.sdp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.ssp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.White.copy(alpha = 0.5f),
            modifier = Modifier.size(20.sdp)
        )
    }
}
