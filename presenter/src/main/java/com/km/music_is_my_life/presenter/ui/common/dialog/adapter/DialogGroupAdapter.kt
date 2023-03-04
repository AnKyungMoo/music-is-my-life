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
import com.km.music_is_my_life.presenter.ui.common.dialog.model.DialogGroupUiModel

class DialogGroupAdapter(
    private var isSelectedItemIndex: Int = 0
): ListAdapter<DialogGroupUiModel, DialogGroupViewHolder>(DialogGroupViewHolder.diffUtil) {

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
    fun onBind(item: DialogGroupUiModel, isSelected: Boolean, notifyDataChanged: () -> Unit) {
        val groupColor = ContextCompat.getColor(binding.root.context, item.color)

        binding.tvGroupName.text = item.title
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
        val diffUtil = object : DiffUtil.ItemCallback<DialogGroupUiModel>() {
            override fun areItemsTheSame(
                oldItem: DialogGroupUiModel,
                newItem: DialogGroupUiModel,
            ): Boolean = (oldItem.title == newItem.title) && (oldItem.color == newItem.color)

            override fun areContentsTheSame(
                oldItem: DialogGroupUiModel,
                newItem: DialogGroupUiModel,
            ): Boolean = oldItem == newItem
        }
    }
}
