package com.zombietank.rockstar

object RecyclerViewHelper {

    fun layout(recyclerView: androidx.recyclerview.widget.RecyclerView) : androidx.recyclerview.widget.RecyclerView {
        recyclerView.measure(0, 0)
        recyclerView.layout(0, 0, 100, 10000)
        return recyclerView
    }
}
