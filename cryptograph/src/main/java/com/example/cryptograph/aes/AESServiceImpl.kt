package com.example.cryptograph.aes

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


class AESServiceImpl : AESService {

    private val symmetricAlgorithm = "AES/CBC/PKCS5PADDING"
    private val keyGenerator: KeyGenerator = KeyGenerator.getInstance("AES")
    private val cipher : Cipher = Cipher.getInstance(symmetricAlgorithm)

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
        withContext(Dispatchers.Default){
            cipher.init(Cipher.ENCRYPT_MODE,key)
            val encryptedBytes = try {
                cipher.doFinal(text.toByteArray())
            }catch (e : Exception){
                e.printStackTrace()
                return@withContext null
            }
            //Here iv stands for Initialisation Vector
            val iv = cipher.iv
            val combinedIvAndEncryptedData = iv+encryptedBytes
            return@withContext Base64.getEncoder().encodeToString(combinedIvAndEncryptedData)
        }
    }

    override suspend fun decryptText(encryptedText: String, key: SecretKey): String? {

    }

}














