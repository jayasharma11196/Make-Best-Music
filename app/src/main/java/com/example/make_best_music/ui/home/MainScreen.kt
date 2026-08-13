package com.example.make_best_music.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Mic : Screen("mic")
    object Community : Screen("community")
    object Library : Screen("library")
    object Profile : Screen("profile")
    object Upload : Screen("upload")
    object Notifications : Screen("notifications")
    object Pricing : Screen("pricing")
    object FAQ : Screen("faq")
    object MoreSettings : Screen("more_settings")
    object CreditDetails : Screen("credit_details")
    object Login : Screen("login")
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) } // Home is default

    Scaffold(
        bottomBar = {
            // Only show bottom bar on main screens
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val showBottomBar = currentRoute in listOf(Screen.Home.route, Screen.Mic.route, Screen.Community.route, Screen.Library.route, Screen.Profile.route)
            
            if (showBottomBar) {
                BottomNavigationBar(navController, selectedItem) { index ->
                    selectedItem = index
                }
            }
        },
        containerColor = Color(0xFF121212)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                com.example.make_best_music.ui.login.SplashScreen(
                    onSplashFinished = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Login.route) {
                com.example.make_best_music.ui.login.LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onClose = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screen.Home.route) { CommunityScreen() }
            composable(Screen.Mic.route) { AICoverScreen() }
            composable(Screen.Community.route) { CreateMusicScreen() }
            composable(Screen.Library.route) { 
                LibraryScreen(onUploadClick = {
                    navController.navigate(Screen.Upload.route)
                }) 
            }
            composable(Screen.Profile.route) { 
                ProfileScreen(
                    onNotificationsClick = { navController.navigate(Screen.Notifications.route) },
                    onPricingClick = { navController.navigate(Screen.Pricing.route) },
                    onFAQClick = { navController.navigate(Screen.FAQ.route) },
                    onMoreSettingsClick = { navController.navigate(Screen.MoreSettings.route) }
                ) 
            }
            composable(Screen.Notifications.route) {
                NotificationScreen(onBackClick = { navController.popBackStack() })
            }
            composable(Screen.Pricing.route) {
                PricingScreen(onBackClick = { navController.popBackStack() })
            }
            composable(Screen.FAQ.route) {
                FAQScreen(onBackClick = { navController.popBackStack() })
            }
            composable(Screen.MoreSettings.route) {
                MoreSettingsScreen(
                    onBackClick = { navController.popBackStack() },
                    onLogoutClick = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onCreditDetailsClick = { navController.navigate(Screen.CreditDetails.route) }
                )
            }
            composable(Screen.CreditDetails.route) {
                CreditDetailsScreen(onBackClick = { navController.popBackStack() })
            }
            composable(Screen.Upload.route) { 
                UploadScreen(onBackClick = {
                    navController.popBackStack()
                })
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf(
        Screen.Home,
        Screen.Mic,
        Screen.Community,
        Screen.Library,
        Screen.Profile
    )
    
    Surface(
        modifier = Modifier.fillMaxWidth().height(60.sdp),
        color = Color(0xFF1C1C2E)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, screen ->
                val iconPainter = when (screen) {
                    Screen.Home -> rememberVectorPainter(Icons.Default.Home)
                    Screen.Mic -> rememberVectorPainter(Icons.Default.Mic)
                    Screen.Community -> painterResource(id = R.drawable.cover_icon)
                    Screen.Library -> painterResource(id = R.drawable.folder)
                    Screen.Profile -> rememberVectorPainter(Icons.Default.Lightbulb)
                    else -> rememberVectorPainter(Icons.Default.Info)
                }

                if (index == 2) { // Special center button
                    Box(
                        modifier = Modifier
                            .size(50.sdp)
                            .offset(y = (-10).sdp)
                            .clickable {
                                onItemSelected(index)
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(iconPainter, contentDescription = null, tint = Color.Unspecified, modifier = Modifier.size(56.sdp))
                    }
                } else {
                    IconButton(onClick = {
                        onItemSelected(index)
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = iconPainter,
                                contentDescription = null,
                                tint = if (selectedItem == index) Color.White else Color.Gray,
                                modifier = Modifier.size(24.sdp)
                            )
                            if (screen == Screen.Mic) {
                                Icon(
                                    Icons.Default.Add,
                                    contentDescription = null,
                                    tint = if (selectedItem == index) Color.White else Color.Gray,
                                    modifier = Modifier.size(10.sdp).align(Alignment.BottomEnd).offset(x = 2.sdp, y = 2.sdp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
