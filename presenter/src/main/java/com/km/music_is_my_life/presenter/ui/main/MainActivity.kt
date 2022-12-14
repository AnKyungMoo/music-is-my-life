package com.km.music_is_my_life.presenter.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.ActivityMainBinding
import com.km.music_is_my_life.presenter.ui.main.adapter.MainTabAdapter
import com.km.music_is_my_life.presenter.ui.search.SearchSongActivity

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val tabTitles = listOf("전체", "그룹별")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initViews()
        /* TODO: 사용처에 맞춰서 위치를 변경해야함 */
        initBottomSheet()
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

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initBottomSheet() {
        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(layoutInflater.inflate(R.layout.song_detail_bottom_sheet, null))
        bottomSheet.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }
}
