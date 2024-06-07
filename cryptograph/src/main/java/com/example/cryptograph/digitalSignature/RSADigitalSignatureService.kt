package com.example.cryptograph.digitalSignature

import com.example.cryptograph.rsa.RSAService
import java.security.PrivateKey
import java.security.PublicKey


class RSADigitalSignatureService(
    private val rsaService: RSAService
): DigitalSignatureService{

    override suspend fun sign(message: String, privateKey: PrivateKey): String? {
        return rsaService.encryptText(message,privateKey)
    }

    override suspend fun verify(signature: String, message: String, publicKey: PublicKey): Boolean {
        val decryptSignature = rsaService.decryptText(signature,publicKey)
        return decryptSignature?.contentEquals(message) ?: false
    }

}