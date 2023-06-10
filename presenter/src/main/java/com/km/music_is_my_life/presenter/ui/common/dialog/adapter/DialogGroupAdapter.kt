package com.km.music_is_my_life.presenter.ui.common.dialog.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.ItemDialogGroupBinding
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel

class DialogGroupAdapter :
    ListAdapter<GroupUiModel, DialogGroupViewHolder>(DialogGroupViewHolder.diffUtil) {
    var isSelectedItemIndex: Int = 0
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogGroupViewHolder {
        return DialogGroupViewHolder(
            ItemDialogGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DialogGroupViewHolder, position: Int) {
        holder.onBind(
            item = currentList[position],
            isSelected = holder.adapterPosition == isSelectedItemIndex
        ) {
            notifyItemChanged(isSelectedItemIndex)
            isSelectedItemIndex = holder.adapterPosition
            notifyItemChanged(isSelectedItemIndex)
        }
    }
}

class DialogGroupViewHolder(
    private val binding: ItemDialogGroupBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: GroupUiModel, isSelected: Boolean, notifyDataChanged: () -> Unit) {
        val groupColor = ContextCompat.getColor(binding.root.context, item.color.colorResId)

        binding.tvGroupName.text = item.groupName
        binding.tvGroupName.setTextColor(groupColor)
        binding.ivGroupIcon.setColorFilter(groupColor)

        if (isSelected) {
            binding.ivGroupCheck.setImageResource(R.drawable.ic_common_check)
            binding.ivGroupCheck.setColorFilter(groupColor)
        } else {
            binding.ivGroupCheck.setImageResource(R.drawable.item_uncheck_background)
            binding.ivGroupCheck.colorFilter = null
        }

        binding.root.setOnClickListener {
            notifyDataChanged()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GroupUiModel>() {
            override fun areItemsTheSame(
                oldItem: GroupUiModel,
                newItem: GroupUiModel,
            ): Boolean = (oldItem.groupName == newItem.groupName) && (oldItem.color == newItem.color)

            override fun areContentsTheSame(
                oldItem: GroupUiModel,
                newItem: GroupUiModel,
            ): Boolean = oldItem == newItem
        }
    }
}
