package com.example.android_developer

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.android_developer.ui.theme.AndroidDeveloperTheme
import com.example.cryptograph.Test


class MainActivity : ComponentActivity() {

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
                Toast.makeText(this, "${Test.test()}", Toast.LENGTH_LONG).show()

            }
        }
    }

}



