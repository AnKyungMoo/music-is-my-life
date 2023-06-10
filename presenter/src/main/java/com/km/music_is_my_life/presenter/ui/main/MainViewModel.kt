package com.km.music_is_my_life.presenter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.model.Group
import com.km.music_is_my_life.domain.usecase.AddGroupUseCase
import com.km.music_is_my_life.domain.usecase.CheckExistGroupUseCase
import com.km.music_is_my_life.domain.usecase.GetFavoriteSongsUseCase
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import com.km.music_is_my_life.presenter.ui.model.SongUiModel
import com.km.music_is_my_life.presenter.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkExistGroupUseCase: CheckExistGroupUseCase,
    private val addGroupUseCase: AddGroupUseCase,
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

    fun insertDefaultGroupIfNeed() {
        viewModelScope.launch {
            val isExistDefaultGroup = checkExistGroupUseCase(Group.DEFAULT_GROUP_NAME)

            if (isExistDefaultGroup.not()) {
                addGroupUseCase(GroupUiModel.DEFAULT_GROUP.toDomainModel())
            }
        }
    }
}
