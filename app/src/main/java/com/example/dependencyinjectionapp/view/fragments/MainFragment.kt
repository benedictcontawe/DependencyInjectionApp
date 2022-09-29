package com.example.dependencyinjectionapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjectionapp.R
import com.example.dependencyinjectionapp.databinding.RecyclerBinder
import com.example.dependencyinjectionapp.model.CustomModel
import com.example.dependencyinjectionapp.view.adapters.CustomAdapter
import com.example.dependencyinjectionapp.view.listeners.CustomListener
import com.example.dependencyinjectionapp.view.listeners.MainListener
import com.example.dependencyinjectionapp.view.models.MainViewModel

public class MainFragment : BaseFragment, CustomListener {

    companion object {
        private val TAG = MainFragment::class.java.getSimpleName()

        fun newInstance(listener : MainListener) : MainFragment = MainFragment(listener)
    }

    private var binder : RecyclerBinder? = null
    private val viewModel : MainViewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
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