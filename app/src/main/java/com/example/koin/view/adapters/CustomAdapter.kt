package com.example.koin.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.R
import com.example.koin.databinding.CustomBinder
import com.example.koin.model.CustomModel
import com.example.koin.view.holders.BaseViewHolder
import com.example.koin.view.holders.CustomViewHolder
import com.example.koin.view.listeners.CustomListener

public class CustomAdapter : RecyclerView.Adapter<BaseViewHolder> {

    companion object {
        private val TAG : String = CustomAdapter.javaClass::class.java.getSimpleName()
    }

    private val customListeners : CustomListener
    private val list : MutableList<CustomModel> = mutableListOf()

    constructor(customListeners : CustomListener) : super() {
        this.customListeners = customListeners
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : BaseViewHolder {
        val binder : CustomBinder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cell_sample,
            parent,
            false
        )
        return CustomViewHolder(customListeners, binder)
    }

    override fun onBindViewHolder(holder : BaseViewHolder, position : Int) {
        holder.bindDataToViewHolder(
            list.get(position),
            position
        )
    }

    override fun getItemId(position : Int) : Long {
        return list.get(position).id.toLong() ?: super.getItemId(position) ?: RecyclerView.NO_ID
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(items : List<CustomModel>) {
        list.clear()
        list.addAll(items)
    }
}