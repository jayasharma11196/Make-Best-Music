package com.example.make_best_music.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Int.sdp: Dp
    @Composable
    get() {
        val context = LocalContext.current
        val widthPixels = context.resources.displayMetrics.widthPixels
        val density = context.resources.displayMetrics.density
        // 300 is a base width, similar to how sdp works
        return ((this * (widthPixels / 300f)) / density).dp
    }

val Int.ssp: TextUnit
    @Composable
    get() {
        val context = LocalContext.current
        val widthPixels = context.resources.displayMetrics.widthPixels
        val density = context.resources.displayMetrics.density
        return ((this * (widthPixels / 300f)) / density).sp
    }
