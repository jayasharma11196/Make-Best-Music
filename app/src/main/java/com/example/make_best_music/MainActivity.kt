package com.example.make_best_music

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.make_best_music.ui.home.MainScreen
import com.example.make_best_music.ui.theme.Make_Best_MusicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Make_Best_MusicTheme {
                MainScreen()
            }
        }
    }
}
