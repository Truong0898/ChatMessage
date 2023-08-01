package com.example.chatmessage.viewmodel

import android.app.Application
import com.example.chatmessage.data.database.ChatDatabase
import com.example.chatmessage.data.repo.ChatRepository
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.google.GoogleEmojiProvider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ChatApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ChatDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ChatRepository(database.messageDao()) }

    override fun onCreate() {
        super.onCreate()
        EmojiManager.install(GoogleEmojiProvider())
    }
}