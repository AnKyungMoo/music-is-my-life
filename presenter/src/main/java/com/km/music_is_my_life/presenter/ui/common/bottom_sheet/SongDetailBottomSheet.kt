package com.km.music_is_my_life.presenter.ui.common.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.km.music_is_my_life.domain.model.Group
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.SongDetailBottomSheetBinding
import com.km.music_is_my_life.presenter.ui.common.dialog.GroupDialog
import com.km.music_is_my_life.presenter.ui.common.dialog.SongDetailBottomSheetListener
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.absoluteValue

@AndroidEntryPoint
class SongDetailBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: SongDetailBottomSheetBinding
    private val viewModel: SongDetailBottomSheetViewModel by viewModels()

    private val songDetailBottomSheetListener = object : SongDetailBottomSheetListener {
        override fun setGroupInfo(group: GroupUiModel) {
            viewModel.group = group

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

        initData()
        bindViews()
        observeData()
    }

    private fun observeData() {
        viewModel.dismissBottomSheetEvent.observe(this) {
            dismiss()
        }
    }

    private fun bindViews() {
        binding.tvSongTitle.text = viewModel.songTitle
        binding.tvSongSinger.text = viewModel.singer
        binding.tvSongNumber.text = viewModel.songNumber
        when (viewModel.gender) {
            SongGender.MAN -> binding.rbMan.isChecked = true
            SongGender.WOMAN -> binding.rbWoman.isChecked = true
        }
        binding.tvGroupName.text = viewModel.group.groupName
        val groupColor = ContextCompat.getColor(requireContext(), viewModel.group.color.colorResId)

        binding.tvGroupName.setTextColor(groupColor)
        binding.ivGroupIcon.setColorFilter(groupColor)
        binding.rg.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_man -> {
                    viewModel.setGenderType(SongGender.MAN)
                }
                R.id.rb_woman -> {
                    viewModel.setGenderType(SongGender.WOMAN)
                }
            }
        }
        binding.btnClose.setOnClickListener {
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnSave.setOnClickListener {
            viewModel.saveFavoriteSong()
        }
        binding.btnPlus.setOnClickListener {
            viewModel.keyUp()
            bindSongKeyViews(viewModel.key)
        }
        binding.btnMinus.setOnClickListener {
            viewModel.keyDown()
            bindSongKeyViews(viewModel.key)
        }
        binding.btnGroupSelector.setOnClickListener {
            val groupDialog = GroupDialog()
            groupDialog.setSongDetailBottomSheetListener(songDetailBottomSheetListener)
            groupDialog.show(parentFragmentManager, TAG)
        }
    }

    private fun initData() {
        viewModel.initData(
            songTitle = configuration.title,
            singer = configuration.singer,
            songNumber = configuration.number,
            gender = configuration.gender,
            key = configuration.key
        )
    }

    private fun bindSongKeyViews(key: Int) {
        val (keyText, isPositiveNumber) = when (key) {
            0 -> "원키" to null
            else -> key.absoluteValue.toString() to (key > 0)
        }

        binding.tvSongKey.text = keyText
        when (isPositiveNumber) {
            null -> binding.ivKeyUpDownIcon.visibility = View.GONE
            else -> {
                binding.ivKeyUpDownIcon.visibility = View.VISIBLE
                val keyUpDownIconRes = when (isPositiveNumber) {
                    true -> R.drawable.ic_home_keyup
                    false -> R.drawable.ic_home_keydown
                }

                binding.ivKeyUpDownIcon.setImageResource(keyUpDownIconRes)
            }
        }
    }

    companion object {
        private const val TAG: String = "SongDetailBottomSheet"

        /* TODO: 더 좋은 구조가 있지 않을까 고민해보자.. */
        private var configuration =
            SongUiModel("", "", "", 0, SongGender.MAN, Group.DEFAULT_GROUP_NAME)

        fun newInstance(configuration: SongUiModel): SongDetailBottomSheet {
            return SongDetailBottomSheet().apply {
                Companion.configuration = configuration
            }
        }
    }
}
