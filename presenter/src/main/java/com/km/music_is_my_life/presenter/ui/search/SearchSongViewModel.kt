package com.km.music_is_my_life.presenter.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.usecase.SearchForMusicUseCase
import com.km.music_is_my_life.presenter.ui.model.SongGender
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import com.km.music_is_my_life.presenter.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSongViewModel @Inject constructor(
    private val searchSongUseCase: SearchForMusicUseCase,
) : ViewModel() {
    private val _searchSongs = MutableLiveData<List<SongUiModel>>()
    val searchSongs: LiveData<List<SongUiModel>>
        get() = _searchSongs

    fun searchSong(keyword: String) {
        /* TODO: 페이징에 대해 고민해보자 */
        viewModelScope.launch {
            _searchSongs.value = searchSongUseCase.invoke(searchWord = keyword).map { music ->
                music.toUiModel(key = 0, gender = SongGender.MAN)
            }
        }
    }
}
