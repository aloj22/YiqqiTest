package com.yiqqi.test.util

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.removeItemsDecorations() {
    if (itemDecorationCount > 0) {
        for (i in 0 until itemDecorationCount) {
            removeItemDecorationAt(i)
        }
    }
}

fun RecyclerView.setItemOffset(offsetBlock: (Rect, Int, RecyclerView) -> Unit) {
    removeItemsDecorations()
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
            offsetBlock.invoke(outRect, itemPosition, parent)
        }
    })
}