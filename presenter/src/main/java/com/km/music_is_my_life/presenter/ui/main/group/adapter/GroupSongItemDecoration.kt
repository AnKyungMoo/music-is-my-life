package com.km.music_is_my_life.presenter.ui.main.group.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.km.music_is_my_life.presenter.ext.dp

class GroupSongItemDecoration : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = 6.dp(view.context)

        if (parent.getChildAdapterPosition(view) == (state.itemCount - 1)) { // 맨 아래
            outRect.bottom = 12.dp(view.context)
        }
    }
}
