package com.example.koin.view

import com.example.koin.model.CustomModel

public interface CustomListeners {

    public fun onUpdate(item : CustomModel, position: Int)

    public fun onDelete(item : CustomModel, position: Int)

}
