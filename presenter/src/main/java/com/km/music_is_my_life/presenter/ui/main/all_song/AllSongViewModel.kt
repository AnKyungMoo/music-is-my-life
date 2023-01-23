package com.km.music_is_my_life.presenter.ui.main.all_song

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllSongViewModel @Inject constructor(
) : ViewModel() {
    private val _songs = MutableLiveData<List<SongUiModel>>()
    val songs: LiveData<List<SongUiModel>>
        get() = _songs
}
