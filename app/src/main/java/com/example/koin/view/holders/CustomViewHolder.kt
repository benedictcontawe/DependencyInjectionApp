package com.example.koin.view.holders

import android.view.View
import com.example.koin.databinding.CustomBinder
import com.example.koin.model.CustomModel
import com.example.koin.view.listeners.CustomListener

public class CustomViewHolder : BaseViewHolder, View.OnClickListener {

    companion object {
        private val TAG : String = CustomViewHolder.javaClass::class.java.getSimpleName()
    }

    private val binder : CustomBinder

    constructor(customListener : CustomListener, customBinder : CustomBinder) : super(customListener, customBinder.root) {
        this.binder = customBinder
    }

    override fun bindDataToViewHolder(model : CustomModel, position : Int) {
        binder.setHolder(model)
        binder.setPosition(position)
        binder.executePendingBindings()
        binder.textView.setText(model.name)
        binder.imageView.setImageResource(model?.icon ?: 0) //binder.imageView.setBackgroundResource(item.icon ?: 0)
        binder.imageView.setImageResource(model.icon?:0)
        binder.buttonEdit.setOnClickListener(this@CustomViewHolder)
        binder.buttonDelete.setOnClickListener(this@CustomViewHolder)
    }

    override fun onClick(view : View?) {
        if (view == binder.buttonEdit) {
            getListener().onUpdate(binder.getHolder(), binder.getPosition())
        } else if(view == binder.buttonDelete) {
            getListener().onDelete(binder.getHolder(), binder.getPosition())
        }
    }
}