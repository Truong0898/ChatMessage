package com.example.chatmessage.view

import java.text.SimpleDateFormat
import java.util.Date

class AppUtils {
    companion object {

        fun getDateFromString(timestamp: String): String? {
            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm")
            val date = Date(timestamp.toLong())
            return sdf.format(date)
        }
        fun getHHmm(timestamp: String): String? {
            val sdf = SimpleDateFormat("hh:mm")
            val date = Date(timestamp.toLong())
            return sdf.format(date)
        }
    }

}
