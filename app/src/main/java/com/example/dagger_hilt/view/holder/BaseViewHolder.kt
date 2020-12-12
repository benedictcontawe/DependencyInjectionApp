package com.example.dagger_hilt.view.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger_hilt.model.CustomModel
import com.example.dagger_hilt.view.CustomListeners

abstract class BaseViewHolder : RecyclerView.ViewHolder {

    companion object {
        private val TAG : String = BaseViewHolder.javaClass::class.java.getSimpleName()
    }
    /**Main */
    private lateinit var context : Context
    private lateinit var customListeners : CustomListeners
    /**Data */
    private var id : Int? = null

    constructor(context: Context, customListeners: CustomListeners, view : View) : super(view) {
        this.context = context
        this.customListeners = customListeners
    }

    fun setId(id : Int) {
        this.id
    }

    fun getId() : Int{
        return id?:0
    }

    fun getContext() : Context {
        return context
    }

    fun getListener() : CustomListeners {
        return customListeners
    }

    abstract fun bindDataToViewHolder(item : CustomModel, position : Int)
}