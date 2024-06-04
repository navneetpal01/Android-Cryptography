package com.example.cryptograph.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.IOException
import kotlin.jvm.Throws


object FileHelper{


    fun getFileName(contentResolver : ContentResolver, uri : Uri) : String{
        var fileName = ""
        val cursor = contentResolver.query(uri,null,null,null,null)
        cursor?.use {
            if (it.moveToFirst()){
                val displayNameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (displayNameIndex != -1){
                    fileName = it.getString(displayNameIndex)
                }
            }
        }
        return fileName
    }

    @Throws(IOException::class)
    fun createCacheFileFromUri(context : Context,uri : Uri,prefix : String,suffix : String) : File?{

        val contentResolver = context.contentResolver
        val customFile : File? = null
        contentResolver.openInputStream(uri)?.use {inputStream ->


        }

    }














}