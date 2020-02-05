package com.zombietank.rockstar

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zombietank.rockstar.shadows.ShadowSwipeRefreshLayout
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(
    sdk = [Build.VERSION_CODES.P],
    application = TestRockstarApplication::class,
    shadows = [ShadowSwipeRefreshLayout::class]
)
abstract class BaseRobolectricTest : KoinTest {
    @get:Rule
    val mockitoJunitRule: MockitoRule = MockitoJUnit.rule()

    fun applicationContext(): Context = ApplicationProvider.getApplicationContext()
}
