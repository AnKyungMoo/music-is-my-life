package com.km.music_is_my_life.presenter.ui.main.group.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.km.music_is_my_life.presenter.ui.ext.dp

class GroupItemDecoration : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == (state.itemCount - 1)) {
            outRect.bottom = 12.dp(view.context)
        } else if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 12.dp(view.context)
        }

        outRect.left = 13.dp(view.context)
        outRect.right = 23.dp(view.context)
    }
}
