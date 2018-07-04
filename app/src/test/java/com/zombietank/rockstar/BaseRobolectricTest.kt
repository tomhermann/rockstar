package com.zombietank.rockstar

import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = RockstarApplication::class)
open class BaseRobolectricTest : KoinTest {

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }
}
