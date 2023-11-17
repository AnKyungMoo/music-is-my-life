package com.km.music_is_my_life.presenter.ui.main.all_song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.presenter.databinding.FragmentAllSongBinding
import com.km.music_is_my_life.presenter.ui.common.bottom_sheet.SongDetailBottomSheet
import com.km.music_is_my_life.presenter.ui.main.MainViewModel
import com.km.music_is_my_life.presenter.ui.main.adapter.MainSongAdapter
import com.km.music_is_my_life.presenter.ui.main.all_song.adapter.AllSongItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllSongFragment : Fragment() {
    private lateinit var binding: FragmentAllSongBinding
    private val activityViewModel: MainViewModel by activityViewModels()
    private val songAdapter: MainSongAdapter = MainSongAdapter {
        SongDetailBottomSheet
            .newInstance(configuration = it)
            .show(parentFragmentManager, AllSongFragment::class.simpleName)
    }

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
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@AllSongFragment.context)
            addItemDecoration(AllSongItemDecoration())
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.songs.collectLatest { songs ->
                    if (songs.isEmpty()) showEmptyView()
                    else hideEmptyView()

                    songAdapter.submitList(songs)
                }
            }
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
