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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
            /* TODO */
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
            val list =
                mutableListOf(GroupUiModel(GroupUiModel.DEFAULT_GROUP_NAME, GroupColor.PURPLE))
            list.addAll(it)

            dialogGroupAdapter.submitList(list)
        }
    }
}
