package com.example.koin.presenter

import com.example.koin.contract.Heater
import com.example.koin.contract.Pump

class CoffeeMaker(val pump : Pump,val lazyHeater : Lazy<Heater>) {

    val heater : Heater by lazy { lazyHeater.value }

    fun brew() {
        heater.on()
        pump.pump()
        println("[_]P cofee! [_]P ")
        heater.off()
    }
}