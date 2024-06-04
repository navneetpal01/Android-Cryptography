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
import com.example.cryptograph.utils.FileHelper
import com.example.cryptograph.utils.osDownloadDirectory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class MainActivity : ComponentActivity() {

    /** For String Encrypt
    val session: CryptoSession = CryptoSessionImpl()
    **/
    private val session : CryptoSession = CryptoSessionImpl()
    private val aesService = session.getAESService()
    private var filePickerLauncher: ActivityResultLauncher<String>? = null

    override fun onStart() {
        super.onStart()
        filePickerLauncher = registerForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                //Replace function replaces the old substring with a new substring
                val fileName = FileHelper.getFileName(contentResolver,uri).replace("","")

                //Split 'file Name' into parts (name, extension) and remove trailing empty strings
                val split = fileName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()

                val fileToEncrypt = FileHelper.createCacheFileFromUri(
                    applicationContext,
                    uri,
                    split[0],
                    "." + split[1]
                )

                val pathToEncrypt = osDownloadDirectory() + fileName + ".enc"
                val encryptedFile = File(pathToEncrypt)
                fileToEncrypt?.let {
                    CoroutineScope(Dispatchers.Main).launch {
                        aesService.encryptFile(it,encryptedFile, key)
                    }
                }
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
                Test2(launcher = filePickerLauncher)
            }
        }
    }
}

// Encrypt File
@Composable
fun Test2(launcher : ActivityResultLauncher<String>?) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                launcher?.launch("*/*")
            }
        ) {
            Text(text = "Click")
        }
    }

}

// Encrypt Text
//@Composable
//fun Test(session: CryptoSession) {
//    val coroutineScope = rememberCoroutineScope()
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(
//            onClick = {
//                val key = session.getAESService().generateKey(128)
//                Log.d("key", "key ${session.getAESService().convertKeyToString(key)}")
//                val textToEncrypt = "Hello world I'm here"
//                coroutineScope.launch {
//                    val encryptedText = session.getAESService().encryptText(textToEncrypt, key)
//                    Log.d("key", "EncryptedText = $encryptedText")
//                    val decryptedText = session.getAESService().decryptText(encryptedText!!, key)
//                    Log.d("", "DecryptedText = $decryptedText")
//                }
//            }
//        ) {
//            Text(text = "Click")
//        }
//    }
//
//}





