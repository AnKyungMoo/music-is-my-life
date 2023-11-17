package com.km.music_is_my_life.presenter.ui.main.group

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.presenter.databinding.FragmentGroupBinding
import com.km.music_is_my_life.presenter.ui.common.bottom_sheet.SongDetailBottomSheet
import com.km.music_is_my_life.presenter.ui.main.MainViewModel
import com.km.music_is_my_life.presenter.ui.main.group.adapter.GroupAdapter
import com.km.music_is_my_life.presenter.ui.main.group.adapter.GroupItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupFragment : Fragment() {
    private lateinit var binding: FragmentGroupBinding
    private val viewModel: GroupViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()
    private val groupAdapter = GroupAdapter {
        SongDetailBottomSheet
            .newInstance(configuration = it)
            .show(parentFragmentManager, GroupFragment::class.simpleName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeData()
        viewModel.loadGroups()
    }

    private fun initViews() {
        binding.rvGroupList.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@GroupFragment.context)
            addItemDecoration(GroupItemDecoration())
        }
    }

    private fun observeData() {
        viewModel.groups.observe(viewLifecycleOwner) {
            groupAdapter.submitList(it)
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                activityViewModel.songs.collectLatest {
                    groupAdapter.updateSongs(it)
                }
            }
        }
    }
}
