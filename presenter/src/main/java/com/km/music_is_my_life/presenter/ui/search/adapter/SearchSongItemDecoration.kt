package com.km.music_is_my_life.presenter.ui.search.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.km.music_is_my_life.presenter.ext.dp

class SearchSongItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.getChildAdapterPosition(view) != (state.itemCount - 1)) {
            outRect.bottom = 6.dp(view.context)
        }
    }
}
