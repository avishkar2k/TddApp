package com.example.tddapp.unit

import com.example.tddapp.Engine
import com.example.tddapp.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Testing [Engine] class
 */
@OptIn(ExperimentalCoroutinesApi::class)
class EngineShould {

    private val engine: Engine = Engine(15, false)
    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun turnOn():Unit = runTest{
        engine.turnOn()
        assertEquals(true,engine.isEngineOn)
    }

    @Test
    fun raiseTemperatureGradually():Unit = runBlocking{
        val flow:Flow<Int> = engine.turnOn()
        val values:List<Int> = flow.toList()
        assertEquals(listOf(25, 50, 95), values)
    }

    @Test
    fun turnOff():Unit = runBlocking{
        engine.turnOn()
        engine.turnOff()

        assertEquals(15, engine.temperature)
        assertEquals(false, engine.isEngineOn)
    }
}