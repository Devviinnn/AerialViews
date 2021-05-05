package com.codingbuffalo.aerialdream.utils

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import java.util.HashSet

object FileHelper {

     fun findAllMedia(context: Context): List<String?> {
        val videoItemHashSet = HashSet<String>()
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val column = "_data"
        val projection = arrayOf(column)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        try {
            cursor!!.moveToFirst()
            do {
                videoItemHashSet.add(cursor.getString(cursor.getColumnIndexOrThrow(column)))
            } while (cursor.moveToNext())
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.i(TAG, "findAllMedia found ${videoItemHashSet.size} files")
        return videoItemHashSet.toList()
    }

    private const val TAG = "FileHelper"
}