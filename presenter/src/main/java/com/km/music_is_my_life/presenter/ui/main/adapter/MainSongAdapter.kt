package com.km.music_is_my_life.presenter.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.ItemSongBinding
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import kotlin.math.absoluteValue

class MainSongAdapter :
    ListAdapter<SongUiModel, MainSongViewHolder>(MainSongViewHolder.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSongViewHolder {
        return MainSongViewHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
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

        bindSongGenderText(item.gender)
        bindSongKeyViews(item.key)
    }

    private fun bindSongGenderText(gender: SongGender) {
        when (gender) {
            SongGender.MAN -> {
                binding.tvGender.text = "남"
                binding.tvGender.setTextColor(ContextCompat.getColor(binding.root.context, R.color.Text10))
            }
            SongGender.WOMAN -> {
                binding.tvGender.text = "여"
                binding.tvGender.setTextColor(ContextCompat.getColor(binding.root.context, R.color.Text11))
            }
        }
    }

    private fun bindSongKeyViews(key: Int) {
        val (keyText, isPositiveNumber) = when (key) {
            0 -> "원키" to null
            else -> key.absoluteValue.toString() to (key > 0)
        }

        when (isPositiveNumber) {
            null -> {
                binding.ivKeyUpDownIcon.visibility = View.GONE
                binding.tvSongKey.visibility = View.GONE
                binding.tvOriginalKey.visibility = View.VISIBLE
            }
            else -> {
                binding.ivKeyUpDownIcon.visibility = View.VISIBLE
                binding.tvSongKey.visibility = View.VISIBLE
                val keyUpDownIconRes = when (isPositiveNumber) {
                    true -> R.drawable.ic_home_keyup
                    false -> R.drawable.ic_home_keydown
                }

                binding.ivKeyUpDownIcon.setImageResource(keyUpDownIconRes)
                binding.tvSongKey.text = keyText
            }
        }
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
