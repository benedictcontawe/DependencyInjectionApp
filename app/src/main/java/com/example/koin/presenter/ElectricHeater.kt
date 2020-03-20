package com.example.koin.presenter

import com.example.koin.contract.Heater

class ElectricHeater : Heater {

    var heating : Boolean = false

    override fun on() {
        println("~ ~ ~ heating ~ ~ ~")
        heating = true
    }

    override fun off() {
        heating = false
    }

    //override fun isHot() : Boolean = heating
    override fun isHot() : Boolean {
        return heating
    }
}