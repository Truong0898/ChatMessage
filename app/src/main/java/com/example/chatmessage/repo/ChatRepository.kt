package com.example.chatmessage.repo

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.example.chatmessage.R
import com.example.chatmessage.model.Message
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat


class ChatRepository {
    private fun getJsonDataFromAsset(context: Context, filename: String): String?{
        var jsonString: String
        try {
            jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
        } catch (ioExeption: IOException) {
            ioExeption.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getListMessage(context: Context, filename: String): MutableLiveData<List<Message>>? {
        var mutableMessage = MutableLiveData<List<Message>>()
        var lsMessage = ArrayList<Message>()
        var jsonData = getJsonDataFromAsset(context,filename)?:null

        var jsonObject = JSONObject(jsonData)
        var jsonArray = jsonObject.getJSONArray("data")

        for (i in 0 until  jsonArray.length()) {
            var messageJsonObject = jsonArray[i] as JSONObject
            val message: Message = Message()
            message.message = messageJsonObject.getString("message")
            message.timeLine = messageJsonObject.getString("timeLine").toLong().toTimeData()
            message.logo = R.drawable.img_1
            message.status = 1

            lsMessage.add(message)
        }
        mutableMessage.postValue(lsMessage)
        return mutableMessage
    }

    fun Long.toTimeData():String {
        val dateTime = java.util.Date(this)
        val format = SimpleDateFormat("HH:mm")
        return format.format(dateTime)
    }

    @BindingAdapter("android:src")
    fun setImageViewResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}