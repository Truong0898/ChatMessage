package com.example.chatmessage.adapter.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.Untils.AppUtils
import com.example.chatmessage.databinding.ChatMessageSendBinding
import com.example.chatmessage.data.model.Message

class SendAHolder(var binding: ChatMessageSendBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message,dateBefore: String) {

        binding.tvDateSend.visibility = if (AppUtils.compareDate(dateBefore, message.timeLine)){
            View.VISIBLE
        } else {
            View.GONE
        }

        binding.message = message

    }
}