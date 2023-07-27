package com.example.chatmessage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.databinding.ChatMessageReceiveBinding
import com.example.chatmessage.databinding.ChatMessageSendBinding
import com.example.chatmessage.model.Message

class MessageAdapter(var lsMessage: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var SEND_MESSAGE = 0
    var RECEIVE_MESSGE = 1

    private lateinit var bindingR: ChatMessageReceiveBinding
    private lateinit var binding: ChatMessageSendBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            binding =
                ChatMessageSendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SendAHolder(binding)
        } else {
            bindingR = ChatMessageReceiveBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ReceiveHolder(bindingR)
        }
    }

    override fun getItemCount(): Int {
        return lsMessage.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var msg = lsMessage.get(position)

        if (holder.itemViewType == SEND_MESSAGE) {
            SendAHolder(binding).bind(msg)
        } else ReceiveHolder(bindingR).bind(msg)
    }

    override fun getItemViewType(position: Int): Int {
        var msg = lsMessage.get(position)
        if (msg.status == SEND_MESSAGE) {
            return SEND_MESSAGE
        }
        return RECEIVE_MESSGE
    }
}