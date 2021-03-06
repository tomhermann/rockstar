package com.zombietank.rockstar.navigation

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zombietank.rockstar.R

class NavigationViewModel : ViewModel() {
    private val selectionSectionId = MutableLiveData<Int>()

    init {
        setSelectedNavigationItemId(R.id.navigation_home)
    }

    fun setSelectedNavigationItemId(@IdRes id: Int) {
        selectionSectionId.value = id
    }

    fun getSelectedNavigationItemId(): LiveData<Int> {
        return selectionSectionId
    }
}
