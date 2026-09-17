package com.example.fotoprompts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.fotoprompts.ui.PromptLibraryApp
import com.example.fotoprompts.ui.theme.FotoPromptsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FotoPromptsTheme {
                PromptLibraryApp()
            }
        }
    }
}
