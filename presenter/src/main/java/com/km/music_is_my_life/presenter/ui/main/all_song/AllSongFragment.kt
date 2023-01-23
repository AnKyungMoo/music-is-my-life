package com.km.music_is_my_life.presenter.ui.main.all_song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.presenter.databinding.FragmentAllSongBinding
import com.km.music_is_my_life.presenter.ui.main.all_song.adapter.AllSongAdapter
import com.km.music_is_my_life.presenter.ui.main.all_song.adapter.AllSongItemDecoration

class AllSongFragment : Fragment() {
    private lateinit var binding: FragmentAllSongBinding
    private val viewModel: AllSongViewModel by viewModels()
    private val allSongAdapter: AllSongAdapter = AllSongAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllSongBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.rvSongList.apply {
            adapter = allSongAdapter
            layoutManager = LinearLayoutManager(this@AllSongFragment.context)
            addItemDecoration(AllSongItemDecoration())
        }
    }

    private fun observeViewModel() {
        viewModel.songs.observe(viewLifecycleOwner) {
            if (it.isEmpty()) showEmptyView()
            else hideEmptyView()

            allSongAdapter.submitList(it)
        }
    }

    private fun showEmptyView() {
        binding.ivEmptyList.visibility = View.VISIBLE
        binding.rvSongList.visibility = View.GONE
    }

    private fun hideEmptyView() {
        binding.ivEmptyList.visibility = View.GONE
        binding.rvSongList.visibility = View.VISIBLE
    }
}
