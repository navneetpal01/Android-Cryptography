package com.example.cryptograph.rsa

import java.security.KeyPair
import java.security.PrivateKey
import java.security.PublicKey


interface RSAService {

    fun generateRSAKeyPair(keySize: Int): KeyPair
    fun convertKeyPairToString(keypair: KeyPair): Pair<String, String>
    fun convertStringToKeyPair(publicKeyString: String, privateKeyString: String): KeyPair

    suspend fun encryptText(text: String, publicKey: PublicKey): String?
    suspend fun encryptText(text: String, privateKey: PrivateKey): String?

    suspend fun decryptText(encryptedText: String, privateKey: PrivateKey): String?
    suspend fun decryptText(encryptedText: String, publicKey: PublicKey): String?


}


