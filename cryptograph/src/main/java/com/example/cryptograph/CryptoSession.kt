package com.example.cryptograph

import com.example.cryptograph.aes.AESService
import com.example.cryptograph.rsa.RSAService


interface CryptoSession{

    fun getAESService() : AESService
    fun getRsaService() : RSAService

}

