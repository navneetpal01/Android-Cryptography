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
import com.example.cryptograph.rsa.RSAService
import com.example.cryptograph.utils.FileHelper
import com.example.cryptograph.utils.osDownloadDirectory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID


class MainActivity : ComponentActivity() {

    /** For String Encrypt
    val session: CryptoSession = CryptoSessionImpl()
     **/


    private val session: CryptoSession = CryptoSessionImpl()
    private val aesService = session.getAESService()
    private val aeskey = aesService.generateKey(128)
    private var filePickerLauncher: ActivityResultLauncher<String>? = null

    private val rsaService = session.getRsaService()
    private val hashService = session.getHashService(CryptoSession.HashFunctions.MD5)


    override fun onStart() {
        super.onStart()
        filePickerLauncher = registerForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                //Replace function replaces the old substring with a new substring
                val fileName = FileHelper.getFileName(contentResolver, uri).replace("", "")


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
                fileToEncrypt?.let {file ->
                    //Running on Main thread cause our functions are using withContext to change Threads
                    CoroutineScope(Dispatchers.Main).launch {
                        aesService.encryptFile(file, encryptedFile, aeskey)?.let { encrypted ->
                            Log.d("file", "Encrypted file path - ${encrypted.path}")
                            val decryptedOutput = osDownloadDirectory() + "decrypted-${
                                UUID.randomUUID().toString().substring(0, 4)
                            }" + fileName
                            val decryptedOutputFile = File(decryptedOutput)
                            aesService.decryptFile(
                                encrypted,
                                decryptedOutputFile,
                                aeskey
                            )?.let {
                                Log.d("file", "Decrypted file path - ${it.path}")
                            }

                        }
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
//                Test2(launcher = filePickerLauncher)
                Test3(service = rsaService)
            }
        }
    }
}

@Composable
fun Test3(service: RSAService) {
    val coroutineScope = rememberCoroutineScope()
    val textToEncrypt = "Hello How are you Meeva"
    val TAG = "Main Activity"
    Button(
        onClick = {
            coroutineScope.launch {
                val keyPair = service.generateRSAKeyPair(2048)
                val converted = service.convertKeyPairToString(keyPair)
                Log.d("app", "Here is the private Key ${converted.second}")
                Log.d("app", "Here is the public Key ${converted.first}")
                val encryptedText = service.encryptText(textToEncrypt,keyPair.public)
                encryptedText?.let { 
                    service.decryptText(it,keyPair.private)?.let {decrypted ->
                        Log.d("app", "decrypted Text ${decrypted}")

                    }
                }
            }
        }
    ) {
        Text(text = "Click")
    }

}


// Encrypt File
@Composable
fun Test2(launcher: ActivityResultLauncher<String>?) {
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





