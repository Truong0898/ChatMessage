package com.example.chatmessage.view

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.R
import com.example.chatmessage.adapter.MessageAdapter
import com.example.chatmessage.databinding.ActivityMainBinding
import com.example.chatmessage.repo.ChatRepository
import com.example.chatmessage.viewmodel.ChatViewModel
import com.example.chatmessage.viewmodel.ChatViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var chatViewModel: ChatViewModel

    private lateinit var messageAdapter: MessageAdapter

    private lateinit var rcvChat : RecyclerView

    private lateinit var binding: ActivityMainBinding

    private lateinit var lsMessage: List<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        rcvChat = binding.rcvChat

        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        rcvChat.layoutManager = linearLayoutManager

        val chatViewModelFactory = ChatViewModelFactory(ChatRepository())
        chatViewModel = ViewModelProvider(this,chatViewModelFactory).get(ChatViewModel::class.java)
        chatViewModel.getMessageFromJson(this,"msg_chat.json")?.observe(this, Observer {
            Log.i("data json", it.get(0).message.toString())
            messageAdapter = MessageAdapter(it)
            rcvChat.adapter = messageAdapter

            rcvChat.smoothScrollToPosition(messageAdapter.itemCount)
        })
    }
}

