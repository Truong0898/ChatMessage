package com.example.chatmessage.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chatmessage.R
import com.example.chatmessage.Untils.AppUtils
import com.example.chatmessage.data.dao.MessageDao
import com.example.chatmessage.data.model.Message
import com.example.chatmessage.viewmodel.MyConst
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.IOException

@Database(entities = [Message::class], version = MyConst.VERSION_DB, exportSchema = false)
abstract class  ChatDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    private class MessageJsonCallback(
        private val scope: CoroutineScope,
        private val context: Context
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { chatDatabase ->
                scope.launch {
                    var messageDao: MessageDao = chatDatabase.messageDao()

                    val lsMessage = getListMessage(context, "msg_chat.json")
                    if (lsMessage != null) {
                        for (i in lsMessage.indices) {
                            messageDao.insertMessage(lsMessage[i])
                        }
                    }

                }
            }
        }

        /**
         * Đọc file json từ folder asset
         * @param context Context
         * @param fileName Tên của file
         * @return Nội dung file dạng String
         */
        private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                // Đọc file json từ folder asset
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getListMessage(context: Context, fileName: String): List<Message>? {
            var lstMessage = ArrayList<Message>()
            var jsonData = getJsonDataFromAsset(context, fileName) ?: null

            // Cast string to JSON
            val jsonObject = JSONObject(jsonData)
            val jsonArray = jsonObject.getJSONArray("data")
            var lsAvatar = ArrayList<Int>()
            for (i in 0 until jsonArray.length()) {
                if (i % 2 == 0) {
                    lsAvatar.add(R.drawable.img)
                } else if (i % 3 == 0) {
                    lsAvatar.add((R.drawable.img_1))
                } else {
                    lsAvatar.add(R.drawable.img)
                }
            }
            for (i in 0 until jsonArray.length()) {
                // Get message from array
                val messageJsonObject = jsonArray[i] as JSONObject
                var message: Message = Message()
                message.logo = AppUtils.convertImageToByteArray(context, lsAvatar[i])
                message.name = messageJsonObject.getString("name")
                message.message = messageJsonObject.getString("message")
                message.timeLine =
                    AppUtils.getDateFromString(messageJsonObject.getString("timeLine")).toString()
                message.status = 1
                // Add message to list
                lstMessage.add(message)
            }
            Log.i("Data list message size", lstMessage.size.toString())

            lstMessage.sortBy {
                it.timeLine
            }

            return lstMessage
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: ChatDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ChatDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatDatabase::class.java,
                    MyConst.DATABASE_NAME
                ).addCallback(MessageJsonCallback(scope, context = context))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}