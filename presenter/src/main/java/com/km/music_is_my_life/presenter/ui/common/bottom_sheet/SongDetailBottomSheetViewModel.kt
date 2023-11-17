package com.km.music_is_my_life.presenter.ui.common.bottom_sheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    var group: GroupUiModel = GroupUiModel.DEFAULT_GROUP

    private val _dismissBottomSheetEvent = MutableLiveData<Unit>()
    val dismissBottomSheetEvent: LiveData<Unit>
        get() = _dismissBottomSheetEvent

    private val _key = MutableLiveData(0)
    val key: LiveData<Int>
        get() = _key


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
        _key.value = key
    }

    fun keyUp() {
        val currentKey = _key.value ?: 0

        if (currentKey + 1 <= MAX_KEY) {
            _key.value = currentKey + 1
        }
    }

    fun keyDown() {
        val currentKey = _key.value ?: 0

        if (currentKey - 1 >= MIN_KEY) {
            _key.value = currentKey - 1
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
                    key = _key.value ?: 0,
                    groupName = group.groupName,
                )
            )

            _dismissBottomSheetEvent.value = Unit
        }
    }

    companion object {
        private const val MAX_KEY = 6
        private const val MIN_KEY = -6
    }
}
