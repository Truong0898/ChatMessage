package com.example.chatmessage.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatmessage.model.Message
import com.example.chatmessage.repo.ChatRepository

class ChatViewModel( private val chatRepository : ChatRepository): ViewModel() {
    fun getMessageFromJson(context: Context, fileName: String): MutableLiveData<List<Message>>? {
        return chatRepository.getListMessage(context, fileName)
    }
}