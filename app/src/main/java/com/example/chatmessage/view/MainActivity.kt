package com.example.chatmessage.view

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.IMultiInstanceInvalidationService.Default
import com.example.chatmessage.R
import com.example.chatmessage.adapter.adapter.MessageAdapter
import com.example.chatmessage.data.model.Message
import com.example.chatmessage.databinding.ActivityMainBinding
import com.example.chatmessage.viewmodel.ChatApplication
import com.example.chatmessage.viewmodel.ChatViewModel
import com.example.chatmessage.viewmodel.ChatViewModelFactory
import com.vanniktech.emoji.EmojiPopup
import java.io.ByteArrayOutputStream
import java.util.Base64
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var chatAdapter: MessageAdapter

    private lateinit var rcvChat: RecyclerView
    private lateinit var rcvAvatarTop: RecyclerView

    private lateinit var edtSend: EditText
    private lateinit var imgTop: ImageView

    private lateinit var emojiPopup: EmojiPopup
    private lateinit var lsMess: List<Message>
    private lateinit var icon: Icon

//    private var lsAvatarTop: ArrayList<ByteArray> = ArrayList()

    private var sizeListMessage: Int = 0

    private val viewModel: ChatViewModel by viewModels {
        ChatViewModelFactory((application as ChatApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        initView()



        viewModel.getMessageFromJson()?.observe(this, Observer {
            it.let {
                chatAdapter = MessageAdapter(it)
                // Set adapter to recyclerview
                rcvChat.adapter = chatAdapter
                Executors.newSingleThreadScheduledExecutor().schedule({
                    rcvChat.smoothScrollToPosition(it.size - 1)
                }, 300, TimeUnit.MILLISECONDS)
//                for (i in it.indices) {
//                    it[i].logo?.let { it1 -> lsAvatarTop.add(it1) }
//                }
                sizeListMessage = it.size

//                val avatarTopAdapter: AvatarTopAdapter = AvatarTopAdapter(lsAvatarTop)
//                rcvAvatarTop.adapter = avatarTopAdapter
            }
        })

    }

    private fun initView() {
        val imgTop = binding.imgTop
        imgTop.setImageResource(R.drawable.nui)
        val btnSend = binding.btnSend
        val btnLike = binding.btnLike
        btnSend.setOnClickListener(this)
        edtSend = binding.edtInputChat
        edtSend.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count != 0 && before == 0) {
                    btnSend.visibility = View.VISIBLE
                    btnLike.visibility = View.GONE
                } else if (count == 0) {
                    btnSend.visibility = View.GONE
                    btnLike.visibility = View.VISIBLE
                    edtSend.hint = "Nhập tin nhắn"
                }
            }

            override fun afterTextChanged(s: Editable?) {}


        })
        rcvChat = binding.rcvChat
        // this creates a vertical layout Manager
        val linearLayoutManager = LinearLayoutManager(this)
//         Scrolling to end when keyboard opens
        linearLayoutManager.stackFromEnd = true
        rcvChat.layoutManager = linearLayoutManager

        emojiPopup = EmojiPopup.Builder.fromRootView(binding.root).build(edtSend)
        binding.imgEmoji.setOnClickListener(this)


//        rcvAvatarTop = binding.rcvAvatar
//        val linearLayoutManagerAvatar =
//            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rcvAvatarTop.layoutManager = linearLayoutManagerAvatar

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSend -> {
                val mess = edtSend.text.toString()
                sendMessage(mess = mess)
            }
            R.id.imgEmoji -> {
                emojiPopup.toggle()
            }

        }
    }

    private fun sendMessage(mess: String) {
        val messSend: Message = Message()
        // Add message to list
        messSend.name = "Me"
        messSend.message = mess
        messSend.timeLine =
            AppUtils.getDateFromString(System.currentTimeMillis().toString()).toString()
        messSend.status = 0
        viewModel.insertMessage(message = messSend)
    }

}




