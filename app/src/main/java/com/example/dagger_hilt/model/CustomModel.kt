package com.example.dagger_hilt.model

import com.example.dagger_hilt.R

class CustomModel {

    var id: Int? = null
    var name: String? = null

    var icon: Int? = null

    constructor(name : String) {
        this.name = name
        this.icon = R.drawable.ic_android_black
    }

    constructor(id : Int, name : String) {
        this.id = id
        this.name = name
        this.icon = R.drawable.ic_android_black
    }

    constructor(id : Int, name : String, icon : Int) {
        this.id = id
        this.name = name
        this.icon = icon
    }
}