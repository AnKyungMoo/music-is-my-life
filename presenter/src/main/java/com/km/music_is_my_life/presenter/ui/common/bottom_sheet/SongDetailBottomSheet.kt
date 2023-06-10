package com.km.music_is_my_life.presenter.ui.common.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.SongDetailBottomSheetBinding
import com.km.music_is_my_life.presenter.ui.common.dialog.GroupDialog
import com.km.music_is_my_life.presenter.ui.common.dialog.SongDetailBottomSheetListener
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import kotlin.math.absoluteValue

class SongDetailBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: SongDetailBottomSheetBinding

    private var songTitle: String = ""
    private var singer: String = ""
    private var songNumber: String = "0"
    private var gender: SongGender = SongGender.MAN
    private var key: Int = 0
    private var group: GroupUiModel = GroupUiModel.DEFAULT_GROUP

    private val songDetailBottomSheetListener = object : SongDetailBottomSheetListener {
        override fun setGroupInfo(group: GroupUiModel) {
            this@SongDetailBottomSheet.group = group

            val groupColor = ContextCompat.getColor(requireContext(), group.color.colorResId)

            binding.tvGroupName.text = group.groupName
            binding.tvGroupName.setTextColor(groupColor)
            binding.ivGroupIcon.setColorFilter(groupColor)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SongDetailBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupContents()
        initButtons()
        onBind()
    }

    private fun initButtons() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnSave.setOnClickListener {
            /* TODO: save */
        }
        binding.btnPlus.setOnClickListener {
            if (key + 1 <= 6) {
                key++
            }
            bindSongKeyViews(key)
        }
        binding.btnMinus.setOnClickListener {
            if (key - 1 >= -6) {
                key--
            }
            bindSongKeyViews(key)
        }
        binding.btnGroupSelector.setOnClickListener {
            val groupDialog = GroupDialog()
            groupDialog.setSongDetailBottomSheetListener(songDetailBottomSheetListener)
            groupDialog.show(parentFragmentManager, TAG)
        }
    }

    private fun setupContents() {
        songTitle = configuration.title
        singer = configuration.singer
        songNumber = configuration.number
        gender = configuration.gender
        key = configuration.key
    }

    private fun onBind() {
        binding.tvSongTitle.text = songTitle
        binding.tvSongSinger.text = singer
        binding.tvSongNumber.text = songNumber
        when (gender) {
            SongGender.MAN -> binding.rbMan.isChecked = true
            SongGender.WOMAN -> binding.rbWoman.isChecked = true
        }
        binding.tvGroupName.text = group.groupName
        val groupColor = ContextCompat.getColor(requireContext(), group.color.colorResId)

        binding.tvGroupName.setTextColor(groupColor)
        binding.ivGroupIcon.setColorFilter(groupColor)
    }

    private fun bindSongKeyViews(key: Int) {
        val (keyText, isPositiveNumber) = when (key) {
            0 -> "원키" to null
            else -> key.absoluteValue.toString() to (key > 0)
        }

        binding.tvSongKey.text = keyText
        if (isPositiveNumber == null) {
            binding.ivKeyUpDownIcon.visibility = View.GONE
        } else {
            binding.ivKeyUpDownIcon.visibility = View.VISIBLE
            val keyUpDownIconRes = when (isPositiveNumber) {
                true -> R.drawable.ic_home_keyup
                false -> R.drawable.ic_home_keydown
            }

            binding.ivKeyUpDownIcon.setImageResource(keyUpDownIconRes)
        }
    }

    companion object {
        private const val TAG: String = "SongDetailBottomSheet"

        /* TODO: 더 좋은 구조가 있지 않을까 고민해보자.. */
        private var configuration = SongUiModel("", "", "", 0, SongGender.MAN)

        fun newInstance(configuration: SongUiModel): SongDetailBottomSheet {
            return SongDetailBottomSheet().apply {
                Companion.configuration = configuration
            }
        }
    }
}
