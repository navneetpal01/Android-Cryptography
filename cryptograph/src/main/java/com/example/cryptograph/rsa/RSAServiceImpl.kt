package com.example.cryptograph.rsa

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64


class RSAServiceImpl : RSAService {

    private val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    val keyFactory = KeyFactory.getInstance("RSA")


    override fun generateRSAKeyPair(keySize: Int): KeyPair {
        keyPairGenerator.initialize(keySize)
        return keyPairGenerator.genKeyPair()
    }

    override fun convertKeyPairToString(keypair: KeyPair): Pair<String, String> {
        //Public encoded returns us Byte Array
        val publicKey = Base64.getEncoder().encodeToString(keypair.public.encoded)
        val privateKey = Base64.getEncoder().encodeToString(keypair.private.encoded)
        return publicKey to privateKey
    }

    override fun convertStringToKeyPair(
        publicKeyString: String,
        privateKeyString: String
    ): KeyPair {
        val publicKeyBytes = Base64.getDecoder().decode(publicKeyString)
        val privateKeyBytes = Base64.getDecoder().decode(privateKeyString)

        val publicKeySpec = X509EncodedKeySpec(publicKeyBytes)
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyBytes)

        val publicKey = keyFactory.generatePublic(publicKeySpec)
        val privateKey = keyFactory.generatePrivate(privateKeySpec)
        return KeyPair(publicKey, privateKey)
    }

    override suspend fun encryptText(text: String, publicKey: PublicKey): String? {
        return withContext(Dispatchers.IO){
            return@withContext ""
        }
    }

    override suspend fun encryptText(text: String, privateKey: PrivateKey): String? {
        TODO("Not yet implemented")
    }

    override suspend fun decryptText(decryptText: String, privateKey: PrivateKey): String? {
        TODO("Not yet implemented")
    }

    override suspend fun decryptText(decryptText: String, publicKey: PublicKey): String? {
        TODO("Not yet implemented")
    }


}