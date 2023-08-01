package com.example.chatmessage.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatmessage.data.model.Message
import org.jetbrains.annotations.NotNull

@Dao
interface MessageDao {
    @Insert
    fun insertMessage(message: Message)

    @Query("SELECT * FROM Message")
    @NotNull
    fun getAllMessage() : LiveData<List<Message>>

    @Query("SELECT * FROM Message WHERE name = :name")
    @NotNull
    fun getMessageByName(name: String) : LiveData<List<Message>>

}