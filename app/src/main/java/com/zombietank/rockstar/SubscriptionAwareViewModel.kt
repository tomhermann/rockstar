package com.zombietank.rockstar

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class SubscriptionAwareViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    internal fun Disposable.disposeOnCleared(): Disposable {
        disposables.add(this)
        return this
    }
}
