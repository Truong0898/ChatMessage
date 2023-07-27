package com.example.chatmessage.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.databinding.ChatMessageReceiveBinding
import com.example.chatmessage.model.Message

class ReceiveHolder(var bindingR: ChatMessageReceiveBinding  ): RecyclerView.ViewHolder(bindingR.root) {
    fun bind(message: Message) {
        bindingR.message = message
    }
}