package com.zombietank.rockstar

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import org.robolectric.shadow.api.Shadow
import org.robolectric.shadows.ShadowViewGroup

@Implements(SwipeRefreshLayout::class)
/***
 * This does not seem to exist for androidx.swiperefreshlayout.widget.SwipeRefreshLayout
 *
 * So I modified the original version from shadows-supportv4
 */
open class ShadowSwipeRefreshLayout : ShadowViewGroup() {
    @RealObject
    internal var realObject: SwipeRefreshLayout? = null

    /**
     * @return OnRefreshListener that was previously set.
     */
    var onRefreshListener: SwipeRefreshLayout.OnRefreshListener? = null
        @Implementation
        protected set(listener) {
            field = listener
            Shadow.directlyOn(realObject, SwipeRefreshLayout::class.java).setOnRefreshListener(listener)
        }
}
