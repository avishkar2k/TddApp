package com.example.tddapp.unit

import com.example.tddapp.Car
import com.example.tddapp.Engine
import com.example.tddapp.utils.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CarShould {

    private var engine: Engine = mock()
    private val car: Car = Car(6.0, engine)

    init {
        runBlocking {
            whenever(engine.turnOn()).thenReturn(flow {
                delay(2000)
                emit(25)
                delay(2000)
                emit(50)
                delay(2000)
                emit(95)
            })
        }
    }

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @Test
    fun looseFuelWhenTurnedOn(): Unit = runBlocking {
        car.turnOn()

        assertEquals(null, 5.5, car.fuel, 0.0)
    }

    @Test
    fun turnOnEngine(): Unit = runBlocking {
        car.turnOn()

        verify(engine, times(1)).turnOn()

    }
}