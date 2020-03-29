package com.example.koin.view

import com.example.koin.model.CustomModel

interface CustomListeners {

    fun onUpdate(item : CustomModel, position: Int)

    fun onDelete(item : CustomModel, position: Int)

}
