package com.example.cryptograph

import com.example.cryptograph.aes.AESService
import com.example.cryptograph.aes.AESServiceImpl
import com.example.cryptograph.hash.HashService
import com.example.cryptograph.hash.MD5Hash
import com.example.cryptograph.hash.SHA256Hash
import com.example.cryptograph.hash.SHA512Hash
import com.example.cryptograph.rsa.RSAService
import com.example.cryptograph.rsa.RSAServiceImpl


class CryptoSessionImpl : CryptoSession{

    override fun getAESService(): AESService {
        return AESServiceImpl()
    }

    override fun getRsaService(): RSAService {
        return RSAServiceImpl()
    }

    override fun getHashService(function: CryptoSession.HashFunctions): HashService {
        return when(function){
            CryptoSession.HashFunctions.SHA256 -> SHA256Hash()
            CryptoSession.HashFunctions.SHA512 -> SHA512Hash()
            CryptoSession.HashFunctions.MD5 -> MD5Hash()
        }
    }

}