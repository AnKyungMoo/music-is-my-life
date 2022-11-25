package com.km.music_is_my_life.presenter.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.km.music_is_my_life.domain.usecase.GetRecentMusicsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getRecentMusicsUseCase: GetRecentMusicsUseCase,
) : ViewModel() {
    fun aa() {
        viewModelScope.launch {
            getRecentMusicsUseCase("tj").forEach {
                Log.d("good", it.title)
            }
        }
    }
}
