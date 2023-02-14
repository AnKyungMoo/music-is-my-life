package com.km.music_is_my_life.presenter.ui.main.group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.presenter.databinding.ItemGroupBinding
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel

class GroupAdapter : ListAdapter<GroupUiModel, GroupViewHolder>(GroupViewHolder.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            ItemGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

class GroupViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(groupItem: GroupUiModel) {
        binding.tvGroupName.text = groupItem.groupName
        binding.tvGroupCount.text = groupItem.count.toString()
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GroupUiModel>() {
            override fun areItemsTheSame(oldItem: GroupUiModel, newItem: GroupUiModel): Boolean {
                return oldItem.groupName == newItem.groupName
            }

            override fun areContentsTheSame(oldItem: GroupUiModel, newItem: GroupUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
