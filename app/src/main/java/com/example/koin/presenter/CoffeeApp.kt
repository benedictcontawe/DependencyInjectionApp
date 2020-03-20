package com.example.koin.presenter

import com.example.koin.contract.Heater
import com.example.koin.contract.Pump
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.dsl.module

class CoffeeApp : KoinComponent {

    val coffeeMaker : CoffeeMaker by inject()

    fun run() {
        //coffeeMaker.brew()
    }

    fun main(vararg args : String) {


    }


}