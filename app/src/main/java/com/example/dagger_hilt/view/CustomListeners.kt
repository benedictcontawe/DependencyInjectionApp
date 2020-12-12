package com.example.dagger_hilt.view

import com.example.dagger_hilt.model.CustomModel

interface CustomListeners {

    fun onUpdate(item : CustomModel, position : Int)

    fun onDelete(item : CustomModel, position : Int)

}