package com.example.koin.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.koin.MainViewModel
import com.example.koin.R
import com.example.koin.databinding.AddBinder
import com.example.koin.model.CustomModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddFragment : BaseFragment() {

    companion object {
        fun newInstance() : AddFragment =
            AddFragment()

        fun getTag() : String {
            return "AddFragment"
        }
    }

    private lateinit var binding : AddBinder
    private val viewModel : MainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.editText.requestFocus()
        showSoftKeyboard()

        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                viewModel.insertItem(CustomModel(binding.editText.text.toString()))
                hideSoftKeyboard()
                activity!!.supportFragmentManager.popBackStack()
            }
        })
    }

    private fun showSoftKeyboard() {
        val inputMethodManager: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}