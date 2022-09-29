package com.example.koin.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.koin.view.models.MainViewModel
import com.example.koin.R
import com.example.koin.databinding.RecyclerBinder
import com.example.koin.model.CustomModel
import com.example.koin.view.listeners.CustomListener
import com.example.koin.view.adapters.CustomAdapter
import com.example.koin.view.listeners.MainListener
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

public class MainFragment : BaseFragment, CustomListener {

    companion object {
        private val TAG = MainFragment::class.java.getSimpleName()

        fun newInstance(listener : MainListener) : MainFragment = MainFragment(listener)
    }

    private var binder : RecyclerBinder? = null
    private val viewModel : MainViewModel by sharedViewModel<MainViewModel>()
    private val adapter : CustomAdapter by lazy { CustomAdapter(this@MainFragment) }
    private val listener : MainListener?
    //private lateinit var itemDecorationHelper: BottomOffsetDecorationHelper

    constructor() {
        listener = null
    }

    constructor(listener : MainListener) {
        this.listener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false)
        return binder?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@MainFragment)
        binder?.recyclerView?.setAdapter(adapter)
        //itemDecorationHelper = BottomOffsetDecorationHelper(requireContext(), R.dimen.extra_scroll)
        //binder?.recyclerView.removeItemDecoration(itemDecorationHelper)
        //binder?.recyclerView.getLayoutManager()?.setAutoMeasureEnabled(false)
        //binder?.recyclerView.scrollToPosition(0)
        //binder?.recyclerView.addItemDecoration(itemDecorationHelper)
        //binder?.recyclerView?.setHasFixedSize(true)
        viewModel.observeItems().observe(getViewLifecycleOwner(), object : Observer<List<CustomModel>> {
            override fun onChanged(list : List<CustomModel>) {
                Log.d(TAG, "ID ${list.map { it.id }}, Name ${list.map { it.name }}")
                binder?.recyclerView?.removeAllViews()
                adapter.setItems(list)
                //adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkIfFragmentLoaded(this@MainFragment)
    }

    override fun onUpdate(item : CustomModel?, position : Int) {
        viewModel.setUpdate(item)
        listener?.launchUpdate()
    }

    override fun onDelete(item : CustomModel?, position : Int) {
        viewModel.deleteItem(item)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}