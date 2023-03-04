package com.km.music_is_my_life.presenter.ui.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.km.music_is_my_life.presenter.R
import com.km.music_is_my_life.presenter.databinding.DialogGroupBinding
import com.km.music_is_my_life.presenter.ui.common.dialog.adapter.DialogGroupAdapter
import com.km.music_is_my_life.presenter.ui.common.dialog.adapter.DialogGroupItemDecoration
import com.km.music_is_my_life.presenter.ui.common.dialog.model.DialogGroupUiModel

class GroupDialog : DialogFragment() {
    private lateinit var binding: DialogGroupBinding
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
        }

        dialogGroupAdapter.submitList(
            listOf(
                DialogGroupUiModel("전체", R.color.Fill07),
                DialogGroupUiModel("아이돌", R.color.Fill08),
                DialogGroupUiModel("힙합", R.color.Fill09),
            )
        )
    }
}
