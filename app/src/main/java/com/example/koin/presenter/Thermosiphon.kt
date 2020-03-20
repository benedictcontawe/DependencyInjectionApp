package com.example.koin.presenter

import com.example.koin.contract.Heater
import com.example.koin.contract.Pump

class Thermosiphon : Pump {

    private lateinit var heater: Heater

    constructor(heater: Heater) {
        this.heater = heater
    }

    override fun pump() {
        if (heater.isHot()) {
            println("=> => pumping => =>")
        }
    }
}