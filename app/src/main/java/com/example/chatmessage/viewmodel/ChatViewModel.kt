package com.example.chatmessage.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatmessage.R
import com.example.chatmessage.data.model.Message
import com.example.chatmessage.data.repo.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel( private val chatRepository : ChatRepository): ViewModel() {
    fun getMessageFromJson(): LiveData<List<Message>>? {
        return chatRepository.lsMessage
    }

    fun insertMessage(message: Message) = viewModelScope.launch {
        chatRepository.insertMessage(message)
    }


}