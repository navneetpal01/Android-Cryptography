package com.example.cryptograph

import com.example.cryptograph.aes.AESService


interface CryptoSession{

    fun getAESService() : AESService

}

