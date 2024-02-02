package com.km.music_is_my_life.presenter.ui.edit.group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.km.music_is_my_life.presenter.databinding.ActivityEditGroupsBinding

class EditGroupsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEditGroupsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditGroupsBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}
