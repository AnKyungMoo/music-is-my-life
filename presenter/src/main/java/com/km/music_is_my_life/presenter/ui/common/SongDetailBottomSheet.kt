package com.km.music_is_my_life.presenter.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.databinding.SongDetailBottomSheetBinding
import com.km.music_is_my_life.presenter.ui.common.dialog.GroupDialog
import com.km.music_is_my_life.presenter.ui.model.SongUiModel

class SongDetailBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: SongDetailBottomSheetBinding

    private var songTitle: String = ""
    private var singer: String = ""
    private var songNumber: String = "0"
    private var gender: SongGender = SongGender.MAN
    private var key: Int = 0

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
        binding.btnPlus.setOnClickListener { key++ }
        binding.btnMinus.setOnClickListener { key-- }
        /* TODO: group 레이아웃 정의되면 클릭범위 수정 */
        binding.layoutGroup.setOnClickListener {
            GroupDialog().show(parentFragmentManager, "")
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
    }

    companion object {
        /* TODO: 더 좋은 구조가 있지 않을까 고민해보자.. */
        private var configuration = SongUiModel("", "", "", 0, SongGender.MAN)

        fun newInstance(configuration: SongUiModel): SongDetailBottomSheet {
            return SongDetailBottomSheet().apply {
                SongDetailBottomSheet.configuration = configuration
            }
        }
    }
}
