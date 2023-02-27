package com.km.music_is_my_life.presenter.ui.search

import android.os.Bundle
import android.text.Editable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.domain.model.SongGender
import com.km.music_is_my_life.presenter.databinding.ActivitySearchSongBinding
import com.km.music_is_my_life.presenter.ui.common.SongDetailBottomSheet
import com.km.music_is_my_life.presenter.ui.search.adapter.SearchSongAdapter
import com.km.music_is_my_life.presenter.ui.search.adapter.SearchSongItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchSongBinding
    private val viewModel: SearchSongViewModel by viewModels()
    private val searchSongAdapter = SearchSongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        observeData()
        showSongDetailBottomSheet()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnClearText.setOnClickListener {
            binding.etSearchSong.text = Editable.Factory().newEditable("")
        }
        binding.rvSongList.apply {
            adapter = searchSongAdapter
            layoutManager = LinearLayoutManager(this@SearchSongActivity)
            addItemDecoration(SearchSongItemDecoration())
        }
        binding.etSearchSong.doAfterTextChanged { editable ->
            editable?.let {
                viewModel.searchSong(it.toString().replace(" ", ""))
            }
        }
    }

    private fun observeData() {
        viewModel.searchSongs.observe(this) {
            searchSongAdapter.submitList(it)
        }
    }

    private fun showSongDetailBottomSheet() {
        /* TODO: 맞는 위치 찾아가자 */
        val bottomSheet =
            SongDetailBottomSheet.newInstance(SongDetailBottomSheet.Companion.Configuration(
                songTitle = "사건의 지평선",
                singer = "윤하",
                songNumber = 111,
                gender = SongGender.WOMAN,
                key = 0,
            ))

        bottomSheet.show(supportFragmentManager, TAG)
    }

    companion object {
        const val TAG = "SearchSongActivity"
    }
}
