package com.km.music_is_my_life.presenter.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.ActivityMainBinding
import com.km.music_is_my_life.presenter.ext.dp
import com.km.music_is_my_life.presenter.ui.main.adapter.MainTabAdapter
import com.km.music_is_my_life.presenter.ui.search.SearchSongActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private val tabTitles = listOf("전체", "그룹별")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initViews()
        viewModel.insertDefaultGroupIfNeed()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initViews() {
        binding.btnSearchSong.setOnClickListener {
            startActivity(Intent(this, SearchSongActivity::class.java))
        }
        binding.viewPager.adapter = MainTabAdapter(this)
        binding.btnEdit.setOnClickListener {
            onClickEditButton()
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun onClickEditButton() {
        val view = layoutInflater.inflate(R.layout.group_popup_window, null)
        val popupWidth = 86.dp(this)
        val popupHeight = 90.dp(this)

        val popupWindow = PopupWindow(
            view,
            popupWidth,
            popupHeight,
            true,
        )

        val location = IntArray(2)
        binding.btnEdit.getLocationInWindow(location)

        popupWindow.showAtLocation(
            view,
            Gravity.END,
            18.dp(this),
            -location[1] + 19.dp(this),
        )
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
//        return true
//    }
}
