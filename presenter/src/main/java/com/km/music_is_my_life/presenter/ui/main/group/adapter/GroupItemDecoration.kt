package com.km.music_is_my_life.presenter.ui.main.group.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.km.music_is_my_life.presenter.ext.dp

class GroupItemDecoration : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 12.dp(view.context)
        }

        outRect.bottom = 12.dp(view.context)
        outRect.left = 18.dp(view.context)
        outRect.right = 18.dp(view.context)
    }
}
