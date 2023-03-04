package com.km.music_is_my_life.presenter.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.usecase.SearchForMusicUseCase
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import com.km.music_is_my_life.presenter.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSongViewModel @Inject constructor(
    private val searchSongUseCase: SearchForMusicUseCase,
) : ViewModel() {
    private val _searchSongs = MutableLiveData<List<SongUiModel>>()
    val searchSongs: LiveData<List<SongUiModel>>
        get() = _searchSongs

    private var searchSongRequestJob: Job? = null

    fun searchSong(keyword: String) {
        searchSongRequestJob?.cancel()

        searchSongRequestJob = viewModelScope.launch {
            delay(SEARCH_INTERVAL)
            _searchSongs.value = searchSongUseCase.invoke(searchWord = keyword).map { music ->
                music.toUiModel()
            }
        }
    }

    companion object {
        private const val SEARCH_INTERVAL = 100L
    }
}
