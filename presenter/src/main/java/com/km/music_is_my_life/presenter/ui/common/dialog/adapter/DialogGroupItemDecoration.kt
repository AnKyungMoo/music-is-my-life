package com.km.music_is_my_life.presenter.ui.common.dialog.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.km.music_is_my_life.presenter.ext.dp

class DialogGroupItemDecoration : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 15.dp(view.context)
        }
        outRect.bottom = if (parent.getChildAdapterPosition(view) == (state.itemCount - 1)) {
             15.dp(view.context)
        } else {
            3.dp(view.context)
        }
        outRect.left = 18.dp(view.context)
        outRect.right = 6.dp(view.context)
    }
}
