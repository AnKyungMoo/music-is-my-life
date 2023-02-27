package com.km.music_is_my_life.presenter.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.databinding.SongDetailBottomSheetBinding

class SongDetailBottomSheet: BottomSheetDialogFragment() {
    private lateinit var binding: SongDetailBottomSheetBinding

    private var songTitle: String = ""
    private var singer: String = ""
    private var songNumber: Int = 0
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
    }

    private fun setupContents() {
        songTitle = configuration.songTitle
        singer = configuration.singer
        songNumber = configuration.songNumber
        gender = configuration.gender
        key = configuration.key
    }

    private fun onBind() {
        binding.tvSongTitle.text = songTitle
        binding.tvSongSinger.text = singer
        binding.tvSongNumber.text = songNumber.toString()
        when (gender) {
            SongGender.MAN -> binding.rbMan.isChecked = true
            SongGender.WOMAN -> binding.rbWoman.isChecked = true
        }
    }

    companion object {
        private var configuration = Configuration()

        /* TODO: 더 좋은 구조가 있지 않을까 고민해보자.. */
        fun newInstance(configuration: Configuration): SongDetailBottomSheet {
            return SongDetailBottomSheet().apply {
                SongDetailBottomSheet.configuration = configuration
            }
        }

        data class Configuration(
            val songTitle: String = "",
            val singer: String = "",
            val songNumber: Int = 0,
            val gender: SongGender = SongGender.MAN,
            val key: Int = 0
        )
    }
}
