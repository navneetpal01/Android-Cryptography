package com.example.android_developer

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.android_developer.ui.theme.AndroidDeveloperTheme
import com.example.cryptograph.CryptoSession
import com.example.cryptograph.CryptoSessionImpl
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    val session: CryptoSession = CryptoSessionImpl()
    private var filePickerLauncher: ActivityResultLauncher<String>? = null

    override fun onStart() {
        super.onStart()
        filePickerLauncher = registerForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {

            }

        }

    }

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
                Test(session = session)
            }
        }
    }
}

// Encrypt File
@Composable
fun Test2(session: CryptoSession) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val key = session.getAESService().generateKey(128)
                coroutineScope.launch {

                }
            }
        ) {
            Text(text = "Click")
        }
    }

}

// Encrypt Text
@Composable
fun Test(session: CryptoSession) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val key = session.getAESService().generateKey(128)
                Log.d("key", "key ${session.getAESService().convertKeyToString(key)}")
                val textToEncrypt = "Hello world I'm here"
                coroutineScope.launch {
                    val encryptedText = session.getAESService().encryptText(textToEncrypt, key)
                    Log.d("key", "EncryptedText = $encryptedText")
                    val decryptedText = session.getAESService().decryptText(encryptedText!!, key)
                    Log.d("", "DecryptedText = $decryptedText")
                }
            }
        ) {
            Text(text = "Click")
        }
    }

}





