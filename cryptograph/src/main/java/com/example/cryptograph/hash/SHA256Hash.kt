package com.example.cryptograph.hash

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest


class SHA256Hash : HashService {

    override fun hash(input: String): ByteArray {
        return hash(input.toByteArray())
    }

    override fun hash(input: ByteArray): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(input)
    }

    override suspend fun hash(file: File): ByteArray {
        return withContext(Dispatchers.IO) {
            val digest = MessageDigest.getInstance("SHA-256")
            val fileInputStream = FileInputStream(file)
            val butter = ByteArray(8192)
            var bytesRead = fileInputStream.read(butter)
            while (bytesRead != -1) {
                digest.update(butter, 0, bytesRead)
                bytesRead = fileInputStream.read(butter)
            }
            fileInputStream.close()
            digest.digest()
        }
    }

}