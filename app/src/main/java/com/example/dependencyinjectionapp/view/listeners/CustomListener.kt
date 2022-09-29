package com.example.dependencyinjectionapp.view.listeners

import com.example.dependencyinjectionapp.model.CustomModel

public interface CustomListener {

    public fun onUpdate(item : CustomModel?, position : Int)

    public fun onDelete(item : CustomModel?, position : Int)
}
