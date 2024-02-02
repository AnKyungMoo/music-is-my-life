package com.km.music_is_my_life.presenter.ui.edit.song

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.km.music_is_my_life.presenter.databinding.ActivityEditSongListBinding

class EditSongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditSongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditSongListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
