package com.example.cryptograph.hash

import java.io.File


interface HashService {
    fun hash(input: String): ByteArray
    fun hash(input: ByteArray): ByteArray
    suspend fun hash(file: File): ByteArray
}