package com.zombietank.rockstar

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(
    sdk = [Build.VERSION_CODES.P],
    application = TestRockstarApplication::class,
    shadows = [ShadowSwipeRefreshLayout::class]
)
abstract class BaseRobolectricTest : KoinTest {

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    fun applicationContext(): Context {
        return ApplicationProvider.getApplicationContext()
    }
}
