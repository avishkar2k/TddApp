package com.example.tddapp

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("PrivatePropertyName")
class Engine(

    var temperature: Int = 15,
    var isEngineOn: Boolean = false
) {
    private val TAG: String?=Engine::class.java.simpleName

    suspend fun turnOn(): Flow<Int> {
       isEngineOn = true

        return flow {
            delay(2000)
            temperature = 25
            emit(temperature)
            delay(2000)
            temperature = 50
            emit(temperature)
            delay(2000)
            temperature = 95
            emit(temperature)
            Log.d(TAG, "turnOn: Engine is on")
        }
    }

    fun turnOff(){
        temperature = 15
        isEngineOn = false
    }
}