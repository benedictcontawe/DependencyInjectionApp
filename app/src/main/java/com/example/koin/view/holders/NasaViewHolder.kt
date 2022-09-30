package com.example.koin.view.holders

import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.example.koin.R
import com.example.koin.databinding.NasaCellBinder
import com.example.koin.model.NasaHolderModel

public class NasaViewHolder : BaseViewHolder {

    companion object {
        private val TAG = NasaViewHolder::class.java.getSimpleName()
    }

    private val binder : NasaCellBinder

    constructor(binder : NasaCellBinder) : super(binder.getRoot()) {
        this.binder = binder
    }

    public override fun bindDataToViewHolder(model : NasaHolderModel, position : Int) {
        binder.setHolder(model)
        binder.setPosition(position)
        binder.executePendingBindings()
        binder.textTitle.setText(binder.getHolder()?.title)
        binder.textCopyright.setText(binder.getHolder()?.copyright)
        binder.textDate.setText(binder.getHolder()?.date)
        binder.textExplanation.setText(binder.getHolder()?.explanation)
        loadPhoto()
    }

    private fun loadPhoto() {
        Glide.with(itemView.getContext())
            .asBitmap()
            .error(R.drawable.ic_image)
            .placeholder(R.drawable.animation_loading)
            .priority(Priority.NORMAL)
            .load(binder.getHolder()?.image)
            .into(binder.image)
    }
}