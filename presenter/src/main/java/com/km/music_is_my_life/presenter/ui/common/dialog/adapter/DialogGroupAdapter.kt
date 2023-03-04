package com.km.music_is_my_life.presenter.ui.common.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.presenter.databinding.ItemDialogGroupBinding
import com.km.music_is_my_life.presenter.ui.common.dialog.model.DialogGroupUiModel

class DialogGroupAdapter :
    ListAdapter<DialogGroupUiModel, DialogGroupViewHolder>(DialogGroupViewHolder.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogGroupViewHolder {
        return DialogGroupViewHolder(
            ItemDialogGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: DialogGroupViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

class DialogGroupViewHolder(
    private val binding: ItemDialogGroupBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: DialogGroupUiModel) {
        binding.tvGroupName.text = item.title
        binding.tvGroupName.setTextColor(ContextCompat.getColor(binding.root.context, item.color))
        binding.ivGroupIcon.setColorFilter(ContextCompat.getColor(binding.root.context, item.color))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DialogGroupUiModel>() {
            override fun areItemsTheSame(
                oldItem: DialogGroupUiModel,
                newItem: DialogGroupUiModel,
            ): Boolean {
                return (oldItem.title == newItem.title) && (oldItem.color == newItem.color)
            }

            override fun areContentsTheSame(
                oldItem: DialogGroupUiModel,
                newItem: DialogGroupUiModel,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
