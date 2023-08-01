package com.example.chatmessage.adapter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.adapter.ViewHolder.AvatarTopViewHolder
import com.example.chatmessage.databinding.ItemRecyclerviewAvatarBinding
import com.example.chatmessage.viewmodel.MyConst

class AvatarTopAdapter(private var lsAvatar: ArrayList<ByteArray>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: ItemRecyclerviewAvatarBinding
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        binding =
            ItemRecyclerviewAvatarBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        return AvatarTopViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return lsAvatar.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var avatarResId = lsAvatar[position]
        AvatarTopViewHolder(binding, context).bind(avatarResId, holder.itemViewType)
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return MyConst.MODE_VIEW_AVATAR_TOP
        }
        return MyConst.MODE_VIEW_AVATAR_BOTTOM
    }
}