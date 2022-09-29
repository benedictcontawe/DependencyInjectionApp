package com.example.koin.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.example.koin.view.models.MainViewModel
import com.example.koin.R
import com.example.koin.databinding.AddBinder
import com.example.koin.model.CustomModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

public class AddFragment : BaseFragment, View.OnClickListener {

    companion object {
        private val TAG = AddFragment::class.java.getSimpleName()

        public fun newInstance() : AddFragment = AddFragment()
    }

    private var binder : AddBinder? = null
    private val viewModel : MainViewModel by sharedViewModel<MainViewModel>()

    constructor() {
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().getOnBackPressedDispatcher().addCallback(this@AddFragment, getHandleOnBackPressed())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_add,container,false)
        return binder?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@AddFragment)
        binder?.editText?.requestFocus()
        showSoftKeyboard(binder?.editText)
        binder?.button?.setOnClickListener(this@AddFragment)
    }

    override fun onClick(view : View?) {
        if (view == binder?.button) {
            viewModel.insertItem(CustomModel(binder?.editText?.text.toString()))
            hideSoftKeyboard()
            requireActivity().getSupportFragmentManager().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkIfFragmentLoaded(this@AddFragment)
    }

    private fun getHandleOnBackPressed() : OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { logDebug(TAG,"handleOnBackPressed() ")
                if (binder?.editText?.getText()?.isNotBlank() == true)
                    binder?.editText?.getText()?.clear()
                else onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        getHandleOnBackPressed().remove()
        super.onDestroy()
    }
}