package com.example.chatmessage.adapter.ViewHolder

import android.content.Context
import android.content.res.ColorStateList
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.chatmessage.R
import com.example.chatmessage.Untils.AppUtils
import com.example.chatmessage.databinding.ItemRecyclerviewAvatarBinding
import com.example.chatmessage.viewmodel.MyConst



class AvatarTopViewHolder(
    private val binding: ItemRecyclerviewAvatarBinding,
    private val context: Context
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(avatarResId: ByteArray, viewType: Int) {
        binding.imgAvatarTop.setImageBitmap(AppUtils.byteArrayToBitmap(avatarResId))
        binding.imgAvatarTop.strokeWidth = 6F
        binding.imgAvatarTop.strokeColor = ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                R.color.circleAvatarWithStrokeOff
            )
        )
        when (viewType) {
            MyConst.MODE_VIEW_AVATAR_BOTTOM -> {
                val layoutParams = binding.imgAvatarTop.layoutParams as LinearLayout.LayoutParams
                layoutParams.setMargins(16, 28, 16, 0)
                binding.imgAvatarTop.layoutParams = layoutParams
                binding.imgAvatarTop.strokeColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        R.color.circleAvatarWithStrokeOn
                    )
                )
            }
        }
    }
}