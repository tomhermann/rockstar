package com.zombietank.rockstar

import androidx.recyclerview.widget.RecyclerView

object RecyclerViewHelper {

    fun layout(recyclerView: androidx.recyclerview.widget.RecyclerView) : androidx.recyclerview.widget.RecyclerView {
        recyclerView.measure(0, 0)
        recyclerView.layout(0, 0, 100, 10000)
        return recyclerView
    }
}
