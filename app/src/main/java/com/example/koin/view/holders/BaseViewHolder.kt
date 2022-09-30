package com.example.koin.view.holders

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.model.NasaHolderModel

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    companion object {
        private val TAG : String = BaseViewHolder.javaClass::class.java.getSimpleName()
    }


    constructor(view : View) : super(view) {

    }

    protected fun getContext() : Context {
        return itemView.getContext()
    }

    abstract fun bindDataToViewHolder(model : NasaHolderModel, position : Int)
}