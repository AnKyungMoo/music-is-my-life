package com.km.music_is_my_life.presenter.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.usecase.AddGroupUseCase
import com.km.music_is_my_life.domain.usecase.CheckExistGroupUseCase
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val checkExistGroupUseCase: CheckExistGroupUseCase,
    private val addGroupUseCase: AddGroupUseCase,
) : ViewModel() {
    fun insertDefaultGroupIfNeed() {
        viewModelScope.launch {
            val isExistDefaultGroup = checkExistGroupUseCase(GroupUiModel.DEFAULT_GROUP_NAME)

            if (isExistDefaultGroup.not()) {
                addGroupUseCase(GroupUiModel.DEFAULT_GROUP.toDomainModel())
            }
        }
    }
}
