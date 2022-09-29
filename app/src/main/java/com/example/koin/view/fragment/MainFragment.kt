package com.example.koin.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koin.MainViewModel
import com.example.koin.R
import com.example.koin.databinding.MainBinder
import com.example.koin.model.CustomModel
import com.example.koin.view.CustomListeners
import com.example.koin.view.MainActivity
import com.example.koin.view.adapter.CustomAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment(), CustomListeners {

    companion object {
        fun newInstance() : MainFragment =
            MainFragment()
    }

    private lateinit var binding: MainBinder
    private val viewModel : MainViewModel by sharedViewModel<MainViewModel>()
    private lateinit var adapter : CustomAdapter
    //private lateinit var itemDecorationHelper: BottomOffsetDecorationHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        //viewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()
        setFloatingActionButton()
    }

    private fun setRecyclerView() {
        adapter = CustomAdapter(context!!, this)
        //itemDecorationHelper = BottomOffsetDecorationHelper(context!!,R.dimen.extra_scroll)

        binding.recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        //binding.recyclerView.removeItemDecoration(itemDecorationHelper)
        binding.recyclerView.adapter = adapter

        viewModel.observeItems().observe(viewLifecycleOwner, object : Observer<List<CustomModel>> {
            override fun onChanged(list : List<CustomModel>) {
                Log.d("MainFragment","ID ${list.map { it.id }}, Name ${list.map { it.name }}")
                binding.recyclerView.removeAllViews()
                adapter.setItems(list)
                adapter.notifyDataSetChanged()
            }
        })
        //binding.recyclerView.scrollToPosition(0)
        //binding.recyclerView.addItemDecoration(itemDecorationHelper)
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun setFloatingActionButton() {
        binding.floatingActionButtonAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                onAdd()
            }
        })
        binding.floatingActionButtonDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                viewModel.deleteAll()
                Toast.makeText(context,"deleteAll()",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onAdd() {
        (activity as MainActivity).callAddFragment()
        //viewModel.setItems()
    }

    override fun onUpdate(item : CustomModel, position : Int) {
        viewModel.setUpdate(item)
        (activity as MainActivity).callUpdateFragment()
    }

    override fun onDelete(item : CustomModel, position : Int) {
        viewModel.deleteItem(item)
    }
}