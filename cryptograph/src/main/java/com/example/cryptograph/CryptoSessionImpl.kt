package com.example.cryptograph

import com.example.cryptograph.aes.AESService
import com.example.cryptograph.aes.AESServiceImpl


class CryptoSessionImpl : CryptoSession{

    override fun getAESService(): AESService {
        return AESServiceImpl()
    }

}