package com.example.cryptograph.aes

import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class AESServiceImpl : AESService{

    private val keyGenerator : KeyGenerator = KeyGenerator.getInstance("AES")

    override fun generateKey(keyLength: Int): SecretKey {
        keyGenerator.init(keyLength)
        return keyGenerator.generateKey()
    }

    override fun convertKeyToString(secretKey: SecretKey): String {
        TODO("Not yet implemented")
    }

    override fun convertStringToKey(key: String): SecretKey {
        TODO("Not yet implemented")
    }

}