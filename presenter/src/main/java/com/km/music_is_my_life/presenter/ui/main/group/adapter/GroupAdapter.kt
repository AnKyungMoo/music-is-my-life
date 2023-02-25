package com.km.music_is_my_life.presenter.ui.main.group.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.databinding.ItemGroupBinding
import com.km.music_is_my_life.presenter.ui.main.adapter.MainSongAdapter
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import com.km.music_is_my_life.presenter.ui.model.SongUiModel

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
    private val songAdapter = MainSongAdapter()
    init {
        binding.root.setOnClickListener {
            binding.rvSongs.visibility = if (binding.rvSongs.isVisible) View.GONE else View.VISIBLE
        }
        binding.rvSongs.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(GroupSongItemDecoration())
        }
        songAdapter.submitList(listOf(
            SongUiModel("1212", "ss", "aae", 0, SongGender.MAN),
            SongUiModel("1212", "ss", "aae", 0, SongGender.MAN),
            SongUiModel("1212", "ss", "aae", 0, SongGender.MAN),
            SongUiModel("1212", "ss", "aae", 0, SongGender.MAN),
            SongUiModel("1212", "ss", "aae", 0, SongGender.MAN),
        ))
    }

    fun onBind(groupItem: GroupUiModel) {
        binding.tvGroupName.text = groupItem.groupName
        binding.tvGroupCount.text = songAdapter.itemCount.toString()
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
