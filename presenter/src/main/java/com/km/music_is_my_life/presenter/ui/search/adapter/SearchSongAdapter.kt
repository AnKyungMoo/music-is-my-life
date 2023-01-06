package com.km.music_is_my_life.presenter.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.presenter.databinding.ItemAddSongBinding

class SearchSongAdapter : RecyclerView.Adapter<SearchSongViewHolder>() {
    /* TODO: 실제 데이터와 연결해야한다. */
    private val songs: List<Music> = listOf(
        Music("", "1234", "사건의", "윤희", "", "", ""),
        Music("", "12", "사건의 지편성", "성시경", "", "", ""),
        Music("", "12345", "사건의 지평선", "윤하", "", "", ""),
        Music("", "1", "사건", "목소리에", "", "", ""),
        Music("", "123", "굿", "돌아보면", "", "", ""),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSongViewHolder {
        return SearchSongViewHolder(
            ItemAddSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchSongViewHolder, position: Int) {
        holder.onBind(songs[position])
    }

    override fun getItemCount(): Int = songs.size
}

class SearchSongViewHolder(private val binding: ItemAddSongBinding) : ViewHolder(binding.root) {
    fun onBind(song: Music) {
        binding.tvSongNumber.text = song.no
        binding.tvSongName.text = song.title
        binding.tvSinger.text = song.singer
    }
}
