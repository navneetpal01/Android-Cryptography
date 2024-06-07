package com.example.cryptograph

import com.example.cryptograph.aes.AESService
import com.example.cryptograph.digitalSignature.DigitalSignatureService
import com.example.cryptograph.hash.HashService
import com.example.cryptograph.rsa.RSAService


interface CryptoSession{

    enum class HashFunctions{
        SHA256,SHA512,MD5
    }
    fun getAESService() : AESService
    fun getRsaService() : RSAService
    fun getHashService(function : HashFunctions) : HashService
    fun getDigitalSignatureService(rsaService: RSAService? = null): DigitalSignatureService


}

