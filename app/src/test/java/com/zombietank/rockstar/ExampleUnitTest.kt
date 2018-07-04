package com.zombietank.rockstar

import com.zombietank.rockstar.navigation.NavigationViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock private lateinit var viewModel: NavigationViewModel

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun assertMockingOfNonOpenClassWorks() {
        assertNotNull(viewModel)
    }
}
