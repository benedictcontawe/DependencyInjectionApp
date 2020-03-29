package com.example.koin.model

import com.example.koin.R

class CustomModel {

    var id: Int? = null
    var name: String? = null

    var icon: Int? = null

    constructor(name : String) {
        this.name = name
        this.icon = R.drawable.ic_launcher_foreground
    }

    constructor(id : Int, name : String) {
        this.id = id
        this.name = name
        this.icon = R.drawable.ic_launcher_foreground
    }

    constructor(id : Int, name : String, icon : Int) {
        this.id = id
        this.name = name
        this.icon = icon
    }
}
