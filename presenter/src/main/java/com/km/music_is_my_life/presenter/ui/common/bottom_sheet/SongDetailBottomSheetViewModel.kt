package com.km.music_is_my_life.presenter.ui.common.bottom_sheet

import androidx.lifecycle.ViewModel
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel

class SongDetailBottomSheetViewModel : ViewModel() {
    var songTitle: String = ""
        private set
    var singer: String = ""
        private set
    var songNumber: String = "0"
        private set
    var gender: SongGender = SongGender.MAN
        private set
    var key: Int = 0
        private set
    var group: GroupUiModel = GroupUiModel.DEFAULT_GROUP

    fun initData(
        songTitle: String,
        singer: String,
        songNumber: String,
        gender: SongGender,
        key: Int,
    ) {
        this.songTitle = songTitle
        this.singer = singer
        this.songNumber = songNumber
        this.gender = gender
        this.key = key
    }

    fun keyUp() {
        if (key + 1 <= MAX_KEY) {
            key++
        }
    }

    fun keyDown() {
        if (key - 1 >= MIN_KEY) {
            key--
        }
    }

    companion object {
        private const val MAX_KEY = 6
        private const val MIN_KEY = -6
    }
}
