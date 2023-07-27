package com.example.chatmessage.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.databinding.ChatMessageSendBinding
import com.example.chatmessage.model.Message

class SendAHolder(var binding: ChatMessageSendBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message) {
        binding.message = message
    }
}