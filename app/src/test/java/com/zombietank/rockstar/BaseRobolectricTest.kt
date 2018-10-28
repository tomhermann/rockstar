package com.zombietank.rockstar

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestRockstarApplication::class)
abstract class BaseRobolectricTest : KoinTest {

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    fun applicationContext(): Context {
        return ApplicationProvider.getApplicationContext()
    }
}
