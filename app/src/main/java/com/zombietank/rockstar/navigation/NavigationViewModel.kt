package com.zombietank.rockstar.navigation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.IdRes
import com.zombietank.rockstar.R

class NavigationViewModel : ViewModel() {
    private val selectedNavigationItemId = MutableLiveData<Int>()

    init {
        setSelectedNavigationItemId(R.id.navigation_home)
    }

    var activeItemId: Int? = null

    fun setSelectedNavigationItemId(@IdRes id: Int) {
        selectedNavigationItemId.value = id
    }

    fun getSelectedNavigationItemId(): LiveData<Int> {
        return selectedNavigationItemId
    }
}
