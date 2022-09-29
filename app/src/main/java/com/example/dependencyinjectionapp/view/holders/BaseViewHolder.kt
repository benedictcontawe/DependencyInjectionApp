package com.example.dependencyinjectionapp.view.holders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dependencyinjectionapp.model.CustomModel
import com.example.dependencyinjectionapp.view.listeners.CustomListener

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    companion object {
        private val TAG : String = BaseViewHolder.javaClass::class.java.getSimpleName()
    }

    private val customListener : CustomListener

    constructor(customListener : CustomListener, view : View) : super(view) {
        this.customListener = customListener
    }

    protected fun getContext() : Context {
        return itemView.getContext()
    }

    protected fun getListener() : CustomListener {
        return customListener
    }

    abstract fun bindDataToViewHolder(model : CustomModel, position : Int)
}