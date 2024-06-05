package com.example.cryptograph

import com.example.cryptograph.aes.AESService
import com.example.cryptograph.aes.AESServiceImpl
import com.example.cryptograph.rsa.RSAService
import com.example.cryptograph.rsa.RSAServiceImpl


class CryptoSessionImpl : CryptoSession{

    override fun getAESService(): AESService {
        return AESServiceImpl()
    }

    override fun getRsaService(): RSAService {
        return RSAServiceImpl()
    }

}