package com.example.chatmessage.data.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_message")
    var idMessage: Int = 0,
    @ColumnInfo(name = "message")
    var message: String="",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = " time_line")
    var timeLine: String = "",
    @ColumnInfo(name = "status")
    var status: Int = 0,
    @ColumnInfo("name = avatar")
    var logo: ByteArray? = null
)

