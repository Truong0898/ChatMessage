package com.example.chatmessage.Untils

import android.R
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.CoroutineStart
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.io.encoding.Base64


class AppUtils {

    companion object {
        fun getDateFromString(timestamp: String): String? {
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
            val date = Date(timestamp.toLong())
            return sdf.format(date)
        }

        fun compareDate(date1: String, date2: String): Boolean {
            val sdf = SimpleDateFormat("dd/MM/yyyy")

            val date1Convert: Date = sdf.parse(date1)
            val date2Convert: Date = sdf.parse(date2)
            Log.i("Compare date 1", date1)
            Log.i("Compare date 2", date2)
            val resultComapre = date1Convert.compareTo(date2Convert)
            when {
                resultComapre == 0 -> {
                    // Cùng ngày
                    return false
                }

                resultComapre > 0 || resultComapre < 0 -> {
                    // Khác ngày
                    return true
                }
            }
            return true
        }

        fun convertImageToByteArray(context: Context, idImage: Int): ByteArray {
            val bitmap = BitmapFactory.decodeResource(context.resources, idImage)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }

        fun byteArrayToBitmap(data: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(data, 0, data.size)
        }


    }
}