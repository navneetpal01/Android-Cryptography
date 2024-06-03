package com.example.cryptograph.aes

import java.util.Base64
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


class AESServiceImpl : AESService {

    private val symmetricAlgorithm = "AES/CBC/PKCS5PADDING"
    private val keyGenerator: KeyGenerator = KeyGenerator.getInstance("AES")

    override fun generateKey(keyLength: Int): SecretKey {
        keyGenerator.init(keyLength)
        return keyGenerator.generateKey()
    }

    override fun convertKeyToString(secretKey: SecretKey): String {
        return Base64.getEncoder().encodeToString(secretKey.encoded)
    }

    override fun convertStringToKey(key: String): SecretKey {
        val decodedBytes = Base64.getDecoder().decode(key)
        return SecretKeySpec(decodedBytes,symmetricAlgorithm)
    }

    override suspend fun encryptText(text: String, key: SecretKey): String? {
        TODO("Not yet implemented")
    }

    override suspend fun decryptText(encryptedText: String, key: SecretKey): String? {
        TODO("Not yet implemented")
    }

}