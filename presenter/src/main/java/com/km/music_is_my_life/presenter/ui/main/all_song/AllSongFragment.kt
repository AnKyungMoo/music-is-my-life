package com.km.music_is_my_life.presenter.ui.main.all_song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.presenter.databinding.FragmentAllSongBinding
import com.km.music_is_my_life.presenter.ui.main.all_song.adapter.AllSongAdapter
import com.km.music_is_my_life.presenter.ui.main.all_song.adapter.AllSongItemDecoration

class AllSongFragment : Fragment() {
    private lateinit var binding: FragmentAllSongBinding
    private val allSongAdapter: AllSongAdapter = AllSongAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllSongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.rvSongList.apply {
            adapter = allSongAdapter
            layoutManager = LinearLayoutManager(this@AllSongFragment.context)
            addItemDecoration(AllSongItemDecoration())
        }
    }
}
