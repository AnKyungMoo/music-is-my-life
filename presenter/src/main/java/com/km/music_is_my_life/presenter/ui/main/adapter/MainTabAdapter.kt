package com.km.music_is_my_life.presenter.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.km.music_is_my_life.presenter.ui.main.all_song.AllSongFragment
import com.km.music_is_my_life.presenter.ui.main.group.GroupFragment

class MainTabAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragments = listOf(AllSongFragment(), GroupFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
