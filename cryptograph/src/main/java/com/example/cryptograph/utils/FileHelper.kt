package com.example.cryptograph.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object FileHelper {
    fun getFileName(contentResolver: ContentResolver, uri: Uri): String {
        var fileName = ""
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayNameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameIndex != -1) {
                    fileName = it.getString(displayNameIndex)
                }
            }
        }
        return fileName
    }

    @Throws(IOException::class)
    fun createCacheFileFromUri(context: Context, uri: Uri, prefix: String, suffix: String): File? {
        val contentResolver = context.contentResolver
        var customFile: File? = null
        contentResolver.openInputStream(uri)?.use { inputStream ->
            var fileName = prefix + suffix
            customFile = File(context.cacheDir, fileName)

            // Check if the file exists and generate a new name if necessary
            var counter = 0
            while (customFile!!.exists()) {
                counter++
                fileName = "${prefix}_$counter$suffix"
                customFile = File(context.cacheDir, fileName)
            }

            FileOutputStream(customFile).use { outputStream ->
                inputStream.copyTo(outputStream, bufferSize = 8 * 1024)
            }
        } ?: run {
            customFile?.delete()
            customFile = null
        }

        return customFile
    }
}

fun Context.osDownloadDirectory(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            .toString() + File.separator
    } else {
        this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separator
    }
}