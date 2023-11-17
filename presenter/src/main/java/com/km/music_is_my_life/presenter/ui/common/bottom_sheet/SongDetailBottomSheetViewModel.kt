package com.km.music_is_my_life.presenter.ui.common.bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.domain.usecase.InsertFavoriteMusicUseCase
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailBottomSheetViewModel @Inject constructor(
    private val insertFavoriteMusicUseCase: InsertFavoriteMusicUseCase
) : ViewModel() {
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

    fun setGenderType(songGender: SongGender) {
        gender = songGender
    }

    fun saveFavoriteSong() {
        viewModelScope.launch {
            insertFavoriteMusicUseCase(
                Music(
                    no = songNumber,
                    title = songTitle,
                    singer = singer,
                    gender = gender,
                    key = key,
                    groupName = group.groupName,
                )
            )
        }
    }

    companion object {
        private const val MAX_KEY = 6
        private const val MIN_KEY = -6
    }
}
