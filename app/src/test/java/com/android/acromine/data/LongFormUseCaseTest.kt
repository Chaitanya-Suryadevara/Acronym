package com.android.acromine.data

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LongFormUseCaseTest {

    private lateinit var longFormRepository: LongFormRepository
    private lateinit var longFormUseCase: LongFormUseCase
    lateinit var testScope: TestCoroutineScope

    @Before
    fun setUp() {
        longFormRepository = LongFormRepository()
        longFormUseCase = LongFormUseCase(longFormRepository)
    }

    @After
    fun cleanUp() {
    }

    @Test
    fun getLongFormRetrofit_withValidInput() = runBlocking {
        val shortForm = "AB"
        val result = longFormUseCase.getLongFormRetrofit(shortForm)
        assertTrue(result.data.toString(), true)
    }

    @Test
    fun getLongFormRetrofit_withEmptyInput() = runBlocking {
        val shortForm = ""
        val result = longFormUseCase.getLongFormRetrofit(shortForm)
        assertFalse(result.data.toString(), false)
    }

    @Test
    fun getLongFormRetrofit_withNonCharInput() = runBlocking {
        val shortForm = "12Ab"
        val result = longFormUseCase.getLongFormRetrofit(shortForm)
        assertFalse(result.data.toString(), false)
    }

    @Test
    fun getLongFormRetrofit_withNumAndSpecialkeyInput() = runBlocking {
        val shortForm = "#@$65"
        val result = longFormUseCase.getLongFormRetrofit(shortForm)
        assertFalse(result.data.toString(), false)
    }

    @Test
    fun getLongFormRetrofit_withLongInput() = runBlocking {
        val shortForm = "ADBFKSGH"
        val result = longFormUseCase.getLongFormRetrofit(shortForm)
        assertFalse(result.data.toString(), false)
    }

    @Test
    fun getLongFormRetrofit_withSpecialCharInput() = runBlocking {
        val shortForm = "#$%@"
        val result = longFormUseCase.getLongFormRetrofit(shortForm)
        assertFalse(result.data.toString(), false)
    }
}