package com.km.music_is_my_life.presenter.ui.common.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.usecase.GetGroupsUseCase
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import com.km.music_is_my_life.presenter.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDialogViewModel @Inject constructor(
    private val getGroupsUseCase: GetGroupsUseCase,
): ViewModel() {
    private val _groups = MutableLiveData<List<GroupUiModel>>()
    val groups: LiveData<List<GroupUiModel>>
        get() = _groups

    fun loadGroups() {
        viewModelScope.launch {
            _groups.value = getGroupsUseCase().map { it.toUiModel() }
        }
    }
}
