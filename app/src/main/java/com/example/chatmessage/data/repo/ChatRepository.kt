package com.example.chatmessage.data.repo

import android.content.Context
import android.widget.ImageView
import androidx.annotation.WorkerThread
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatmessage.R
import com.example.chatmessage.data.dao.MessageDao
import com.example.chatmessage.data.model.Message
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat


class ChatRepository(private val messageDao: MessageDao) {

    val lsMessage: LiveData<List<Message>> = messageDao.getAllMessage()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMessage(message: Message) {
        messageDao.insertMessage(message)
    }


}