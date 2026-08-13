package com.example.make_best_music.ui.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.make_best_music.R
import com.example.make_best_music.ui.utils.sdp
import com.example.make_best_music.ui.utils.ssp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onClose: () -> Unit,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1C1B33),
                        Color(0xFF121212)
                    )
                )
            )
    ) {
        // Close Button
        IconButton(
            onClick = { onClose() },
            modifier = Modifier
                .padding(16.sdp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.White
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.sdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            LogoSection()

            Spacer(modifier = Modifier.height(30.sdp))

            Text(
                text = "Log In MakeBestMusic",
                fontSize = 22.ssp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.sdp))

            Text(
                text = "Create the best music",
                fontSize = 14.ssp,
                color = Color(0xFFB0B0B0),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(150.sdp)) // Adjusted spacing

            // Google Login Button
            Button(
                onClick = { 
                    viewModel.onGoogleLoginClicked()
                    onLoginSuccess() 
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.sdp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = CircleShape,
                contentPadding = PaddingValues(horizontal = 16.sdp)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.sdp)
                            .align(Alignment.CenterStart)
                    )
                    
                    Text(
                        text = "Login with Google",
                        color = Color.Black,
                        fontSize = 14.ssp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.sdp))

            // OR Divider
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                DashedDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "OR",
                    color = Color.Gray,
                    fontSize = 10.ssp,
                    modifier = Modifier.padding(horizontal = 12.sdp)
                )
                DashedDivider(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(30.sdp))

            // Social Icons
            Row(
                horizontalArrangement = Arrangement.spacedBy(25.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Login with Facebook",
                    modifier = Modifier
                        .size(24.sdp)
                        .clickable {
                            viewModel.onFacebookLoginClicked()
                            onLoginSuccess()
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = "Login with X",
                    modifier = Modifier
                        .size(24.sdp)
                        .clickable {
                            viewModel.onXLoginClicked()
                            onLoginSuccess()
                        }
                )
            }

            Spacer(modifier = Modifier.height(40.sdp))

            // Footer Text
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("By continuing, you agree to ")
                }
                withStyle(style = SpanStyle(color = Color.Gray, textDecoration = TextDecoration.Underline)) {
                    append("Privacy Policy")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" & ")
                }
                withStyle(style = SpanStyle(color = Color.Gray, textDecoration = TextDecoration.Underline)) {
                    append("Terms")
                }
            }
            Text(
                text = annotatedString,
                fontSize = 10.ssp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LogoSection() {
    Image(
        painter = painterResource(id = R.drawable.app_logo),
        contentDescription = "App Logo",
        modifier = Modifier
            .size(120.sdp)
            .clip(CircleShape)
    )
}

@Composable
fun SocialIcon(iconRes: Int, backgroundColor: Color, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier.size(45.sdp),
        shape = CircleShape,
        color = backgroundColor
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.sdp)
            )
        }
    }
}

@Composable
fun DashedDivider(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.height(1.dp)) {
        drawLine(
            color = Color.Gray.copy(alpha = 0.5f),
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f),
            strokeWidth = 1.dp.toPx()
        )
    }
}
