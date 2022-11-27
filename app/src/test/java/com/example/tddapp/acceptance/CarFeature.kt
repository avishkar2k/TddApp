package com.example.tddapp.acceptance

import com.example.tddapp.Car
import com.example.tddapp.Engine
import com.example.tddapp.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CarFeature {

    private val engine: Engine = Engine(95, false)
    private val car: Car = Car(6.0, engine)

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun carIsLoosingFuelWhenTurnedOn(): Unit = runBlocking {
        car.turnOn()
        assertEquals(null, 5.5, car.fuel, 0.0)
    }

    @Test
    fun carEngineTurnedOnAndTemperatureIncreases(): Unit = runBlocking {
        car.turnOn()
        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(25, car.engine.temperature)
        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(50, car.engine.temperature)
        coroutineTestRule.advanceTimeBy(2000)
        assertEquals(95, car.engine.temperature)
        assertTrue(car.engine.isEngineOn)
    }
}