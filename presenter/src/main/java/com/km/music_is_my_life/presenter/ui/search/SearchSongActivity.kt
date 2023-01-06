package com.km.music_is_my_life.presenter.ui.search

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.km.music_is_my_life.presenter.databinding.ActivitySearchSongBinding

class SearchSongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchSongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener { finish() }
        binding.btnClearText.setOnClickListener {
            binding.etSearchSong.text = Editable.Factory().newEditable("")
        }
    }
}
