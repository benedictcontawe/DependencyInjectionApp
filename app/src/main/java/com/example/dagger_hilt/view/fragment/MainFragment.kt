package com.example.dagger_hilt.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger_hilt.MainViewModel
import com.example.dagger_hilt.R
import com.example.dagger_hilt.databinding.MainBinder
import com.example.dagger_hilt.model.CustomModel
import com.example.dagger_hilt.view.CustomListeners
import com.example.dagger_hilt.view.MainActivity
import com.example.dagger_hilt.view.adapter.CustomAdapter

class MainFragment : Fragment(), CustomListeners {

    companion object {
        private val TAG : String = MainFragment.javaClass::class.java.getSimpleName()

        fun getTag() : String {
            return TAG
        }

        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    private lateinit var binding : MainBinder
    private val mainAndroidViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter : CustomAdapter
    //private lateinit var itemDecorationHelper: BottomOffsetDecorationHelper

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.setMainViewModel(mainAndroidViewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()
        setFloatingActionButton()
        //binding.getMainViewModel().setItems()
    }

    private fun setRecyclerView() {
        adapter = CustomAdapter(requireContext(), this@MainFragment)
        //itemDecorationHelper = BottomOffsetDecorationHelper(context!!,R.dimen.extra_scroll)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        //binding.recyclerView.removeItemDecoration(itemDecorationHelper)
        binding.recyclerView.adapter = adapter

        (binding.recyclerView.layoutManager as LinearLayoutManager).setAutoMeasureEnabled(false)

        binding.getMainViewModel()?.getItems()?.observe(getViewLifecycleOwner(), object : Observer<List<CustomModel>> {
            override fun onChanged(list : List<CustomModel>) {
                Log.d("MainFragment","ID ${list.map { it.id }}, Name ${list.map { it.name }}")
                binding.recyclerView.removeAllViews()
                adapter.setItems(list)
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
                binding.getMainViewModel()?.deleteAll()
                Toast.makeText(context,"deleteAll()", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onAdd() {
        (activity as MainActivity).callAddFragment()
        //viewModel.setItems()
    }

    override fun onUpdate(item : CustomModel, position : Int) {
        binding.getMainViewModel()?.setUpdate(item)
        (activity as MainActivity).callUpdateFragment()
    }

    override fun onDelete(item : CustomModel, position : Int) {
        binding.getMainViewModel()?.deleteItem(item)
    }
}