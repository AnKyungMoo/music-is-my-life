package com.km.music_is_my_life.presenter.ui.main.all_song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.usecase.GetFavoriteSongsUseCase
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import com.km.music_is_my_life.presenter.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class AllSongViewModel @Inject constructor(
    getFavoriteSongsUseCase: GetFavoriteSongsUseCase,
) : ViewModel() {
    val songs: StateFlow<List<SongUiModel>> = getFavoriteSongsUseCase().map { songs ->
        songs.map { song ->
            song.toUiModel()
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        emptyList()
    )
}
