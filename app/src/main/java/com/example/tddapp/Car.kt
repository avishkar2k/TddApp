package com.example.tddapp

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("PrivatePropertyName")
class Car(
    var fuel: Double,
    var engine: Engine
) {


    private val TAG: String? = Car::class.java.simpleName

    fun turnOn() {
        fuel -= 0.5
        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn().collect { temperature ->
                Log.d(TAG, "turnOn: $temperature")
            }
        }
    }
}