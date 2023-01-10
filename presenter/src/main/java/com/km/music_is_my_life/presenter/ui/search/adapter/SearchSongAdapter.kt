package com.km.music_is_my_life.presenter.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.km.music_is_my_life.presenter.databinding.ItemAddSongBinding
import com.km.music_is_my_life.presenter.ui.model.SongUiModel

class SearchSongAdapter : ListAdapter<SongUiModel, SearchSongViewHolder>(SearchSongViewHolder.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSongViewHolder {
        return SearchSongViewHolder(
            ItemAddSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchSongViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}

class SearchSongViewHolder(private val binding: ItemAddSongBinding) : ViewHolder(binding.root) {
    fun onBind(song: SongUiModel) {
        binding.tvSongNumber.text = song.number
        binding.tvSongName.text = song.title
        binding.tvSinger.text = song.singer
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
