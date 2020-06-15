package com.zombietank.rockstar

import android.content.Context
import androidx.annotation.StringRes
import androidx.test.core.app.ApplicationProvider
import strikt.api.Assertion

fun Assertion.Builder<CharSequence?>.isEqualToResource(@StringRes resourceId: Int) {
    val expectedValue = getStringResource(resourceId)
    assert("is equal to %s", expectedValue) { actual ->
        when (actual) {
            expectedValue -> pass()
            else -> fail(actual)
        }
    }
}

private fun getStringResource(@StringRes resourceId: Int) =
    ApplicationProvider.getApplicationContext<Context>().getString(resourceId)
