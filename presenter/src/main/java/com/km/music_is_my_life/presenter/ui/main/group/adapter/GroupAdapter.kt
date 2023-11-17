package com.km.music_is_my_life.presenter.ui.main.group.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

class GroupAdapter(
    private val onClickSongItem: (SongUiModel) -> Unit,
) : ListAdapter<GroupUiModel, GroupViewHolder>(GroupViewHolder.diffUtil) {
    private val songs = mutableListOf<SongUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder(
            binding = ItemGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickSongItem = onClickSongItem,
        )
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.onBind(currentList[position], songs)
    }

    fun updateSongs(songs: List<SongUiModel>) {
        this.songs.clear()
        this.songs.addAll(songs)
        notifyDataSetChanged()
    }
}

class GroupViewHolder(
    private val binding: ItemGroupBinding,
    private val onClickSongItem: (SongUiModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    private val songAdapter = MainSongAdapter {
        onClickSongItem(it)
    }
    init {
        /* TODO: 그룹 접기 아이콘 변경 */
        binding.root.setOnClickListener {
            binding.rvSongs.visibility = if (binding.rvSongs.isVisible) View.GONE else View.VISIBLE
        }
        binding.rvSongs.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(GroupSongItemDecoration())
        }
    }

    fun onBind(groupItem: GroupUiModel, songs: List<SongUiModel>) {
        val songsInGroup = songs.filter { it.groupName == groupItem.groupName }

        binding.tvGroupName.text = groupItem.groupName
        binding.tvGroupCount.text = songsInGroup.size.toString()
        binding.ivGroupIcon.setColorFilter(
            ContextCompat.getColor(
                binding.root.context,
                groupItem.color.colorResId,
            )
        )
        /* TODO: 이렇게하면 스크롤할 때 버벅거리는 이슈가 생기지 않을까..? 데이터 양을 늘려서 테스트 필요 */
        songAdapter.submitList(songsInGroup)
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
