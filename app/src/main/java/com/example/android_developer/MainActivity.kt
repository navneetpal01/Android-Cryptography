package com.example.android_developer

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_developer.ui.theme.AndroidDeveloperTheme
import com.example.cryptograph.CryptoSession
import com.example.cryptograph.CryptoSessionImpl


class MainActivity : ComponentActivity() {

    val session : CryptoSession = CryptoSessionImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            AndroidDeveloperTheme {

            }
        }
    }

}



