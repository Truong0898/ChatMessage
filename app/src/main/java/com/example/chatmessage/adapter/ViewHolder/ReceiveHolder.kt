package com.example.chatmessage.adapter.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.Untils.AppUtils
import com.example.chatmessage.databinding.ChatMessageReceiveBinding
import com.example.chatmessage.data.model.Message

class ReceiveHolder(var bindingR: ChatMessageReceiveBinding  ): RecyclerView.ViewHolder(bindingR.root) {
    fun bind(message: Message, dateBefore: String) {
        bindingR.tvDateRec.visibility = if (AppUtils.compareDate(dateBefore,message.timeLine)){
            View.VISIBLE
        } else {
            View.GONE
        }

        bindingR.imgRec.setImageBitmap(message.logo?.let {
            AppUtils.byteArrayToBitmap(it)
        })
        bindingR.message = message
    }
}