package com.example.koin.view.listeners

import com.example.koin.model.CustomModel

public interface CustomListener {

    public fun onUpdate(item : CustomModel?, position: Int)

    public fun onDelete(item : CustomModel?, position: Int)
}
