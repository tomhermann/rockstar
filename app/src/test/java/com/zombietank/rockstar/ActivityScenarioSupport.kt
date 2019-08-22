package com.zombietank.rockstar

import android.app.Activity
import androidx.test.core.app.ActivityScenario

object ActivityScenarioSupport {
    inline fun <reified T : Activity> launch(check: (ActivityScenario<T>) -> ActivityScenario<T>) =
        ActivityScenario
            .launch(T::class.java)
            .use { check.invoke(it) }
}
