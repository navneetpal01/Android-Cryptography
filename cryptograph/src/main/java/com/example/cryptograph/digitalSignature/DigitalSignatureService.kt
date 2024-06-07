package com.example.cryptograph.digitalSignature

import java.security.PrivateKey
import java.security.PublicKey


interface DigitalSignatureService{


    suspend fun sign(message : String,privateKey: PrivateKey) : String?
    suspend fun verify(signature : String,message: String,publicKey: PublicKey) : Boolean
}