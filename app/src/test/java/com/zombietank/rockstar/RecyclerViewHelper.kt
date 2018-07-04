package com.zombietank.rockstar

import android.support.v7.widget.RecyclerView

object RecyclerViewHelper {

    fun layout(recyclerView: RecyclerView) : RecyclerView {
        recyclerView.measure(0, 0)
        recyclerView.layout(0, 0, 100, 10000)
        return recyclerView
    }
}
