package com.km.music_is_my_life.presenter.ui.main.all_song.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.presenter.ui.ext.dp

class AllSongItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = 18.dp(view.context)
        outRect.right = 18.dp(view.context)

        if (parent.getChildAdapterPosition(view) == (state.itemCount - 1)) { // 맨 아래
            outRect.bottom = 18.dp(view.context)
        } else {
            outRect.bottom = 6.dp(view.context)
        }

        if (parent.getChildAdapterPosition(view) == 0) { // 맨 위
            outRect.top = 18.dp(view.context)
        }
    }
}
