package com.example.cryptograph.aes

import java.io.File
import javax.crypto.SecretKey


interface AESService {

    fun generateKey(keyLength: Int): SecretKey
    fun convertKeyToString(secretKey: SecretKey): String
    fun convertStringToKey(key: String): SecretKey
    suspend fun encryptText(text: String, key: SecretKey): String?
    suspend fun decryptText(encryptedText: String, key: SecretKey): String?
    suspend fun encryptFile(inputFile: File, outputFile: File, key: SecretKey): File?
    suspend fun decryptFile(encryptedFile: File, outputFile: File, key: SecretKey): File?

}