package com.km.music_is_my_life.presenter.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.presenter.databinding.ItemSongBinding
import com.km.music_is_my_life.presenter.ui.model.SongUiModel

class MainSongAdapter :
    ListAdapter<SongUiModel, MainSongViewHolder>(MainSongViewHolder.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSongViewHolder {
        return MainSongViewHolder(
            ItemSongBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: MainSongViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

class MainSongViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: SongUiModel) {
        binding.tvSongNumber.text = item.number
        binding.tvSongName.text = item.title
        binding.tvSinger.text = item.singer
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SongUiModel>() {
            override fun areItemsTheSame(oldItem: SongUiModel, newItem: SongUiModel): Boolean {
                return oldItem.number == newItem.number
            }

            override fun areContentsTheSame(oldItem: SongUiModel, newItem: SongUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
