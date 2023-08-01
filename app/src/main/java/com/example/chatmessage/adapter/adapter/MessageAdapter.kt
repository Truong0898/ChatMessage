package com.example.chatmessage.adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.adapter.ViewHolder.ReceiveHolder
import com.example.chatmessage.adapter.ViewHolder.SendAHolder
import com.example.chatmessage.databinding.ChatMessageReceiveBinding
import com.example.chatmessage.databinding.ChatMessageSendBinding
import com.example.chatmessage.data.model.Message
import com.example.chatmessage.viewmodel.MyConst

class MessageAdapter(var lsMessage: List<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var bindingR: ChatMessageReceiveBinding
    private lateinit var binding: ChatMessageSendBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == MyConst.SENT_MESSAGE) {
            binding =
                ChatMessageSendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SendAHolder(binding)
        } else {
            bindingR = ChatMessageReceiveBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
            return ReceiveHolder(bindingR)
        }
    }

    override fun getItemCount(): Int {
        return lsMessage.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg = lsMessage[position]
        val dateBefore = if (position == 0) {
            msg.timeLine
        } else {
            lsMessage[position -  1].timeLine
        }

        if (holder.itemViewType == MyConst.SENT_MESSAGE) {
            SendAHolder(binding).bind(msg, dateBefore)
        } else ReceiveHolder(bindingR).bind(msg, dateBefore)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = lsMessage.get(position)
        if (msg.status == MyConst.SENT_MESSAGE) {
            return MyConst.SENT_MESSAGE
        }
        return MyConst.RECIVED_MESSAGE
    }
}