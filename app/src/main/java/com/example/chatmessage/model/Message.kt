package com.example.chatmessage.model

import androidx.annotation.DrawableRes

data class Message(
    var message: String="",
    var name: String = "",
    var timeLine: String = "",
    var status: Int = 0,
    @DrawableRes var logo: Int = 0
)

