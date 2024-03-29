package com.km.music_is_my_life.presenter.ui.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.presenter.databinding.DialogGroupBinding
import com.km.music_is_my_life.presenter.ui.common.dialog.adapter.DialogGroupAdapter
import com.km.music_is_my_life.presenter.ui.common.dialog.adapter.DialogGroupItemDecoration
import com.km.music_is_my_life.presenter.ui.model.GroupColor
import com.km.music_is_my_life.presenter.ui.model.GroupUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupDialog : DialogFragment() {
    private lateinit var binding: DialogGroupBinding
    private val viewModel: GroupDialogViewModel by viewModels()
    private val dialogGroupAdapter = DialogGroupAdapter()
    private var songDetailBottomSheetListener: SongDetailBottomSheetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DialogGroupBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeData()

        viewModel.loadGroups()
    }

    private fun initViews() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnConfirm.setOnClickListener {
            songDetailBottomSheetListener?.setGroupInfo(
                viewModel.groups.value?.get(dialogGroupAdapter.isSelectedItemIndex)
                    ?: GroupUiModel.DEFAULT_GROUP
            )
            dismiss()
        }
        binding.rvGroups.apply {
            adapter = dialogGroupAdapter
            layoutManager = LinearLayoutManager(this@GroupDialog.context)
            addItemDecoration(DialogGroupItemDecoration())
            itemAnimator = null
        }
    }

    private fun observeData() {
        viewModel.groups.observe(viewLifecycleOwner) {
            dialogGroupAdapter.submitList(it)
        }
    }

    fun setSongDetailBottomSheetListener(songDetailBottomSheetListener: SongDetailBottomSheetListener) {
        this.songDetailBottomSheetListener = songDetailBottomSheetListener
    }
}
