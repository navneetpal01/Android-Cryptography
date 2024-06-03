package com.example.cryptograph.aes

import javax.crypto.SecretKey


interface AESService {

    fun generateKey(keyLength: Int): SecretKey
    fun convertKeyToString(secretKey: SecretKey): String
    fun convertStringToKey(key: String): SecretKey
    suspend fun encryptText(text: String, key: SecretKey): String?
    suspend fun decryptText(encryptedText : String, key : SecretKey): String?

}