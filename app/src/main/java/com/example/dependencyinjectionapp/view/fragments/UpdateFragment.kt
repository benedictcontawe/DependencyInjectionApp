package com.example.dependencyinjectionapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dependencyinjectionapp.R
import com.example.dependencyinjectionapp.databinding.UpdateBinder
import com.example.dependencyinjectionapp.model.CustomModel
import com.example.dependencyinjectionapp.util.Coroutines
import com.example.dependencyinjectionapp.view.models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

public class UpdateFragment : BaseDialogFragment, View.OnClickListener {

    companion object {
        private val TAG = UpdateFragment::class.java.getSimpleName()

        fun newInstance(): UpdateFragment = UpdateFragment()
    }

    private var binder : UpdateBinder? = null
    private val viewModel : MainViewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }//private val viewModel : MainViewModel by viewModels<MainViewModel>()

    constructor() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        getDialog()?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        return binder?.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@UpdateFragment)
        viewModel.observeUpdate().observe(getViewLifecycleOwner(), object : Observer<CustomModel> {
            override fun onChanged(item : CustomModel) {
                binder?.editText?.setText(item.name)
                binder?.editText?.requestFocus()
                binder?.editText?.selectAll()
                showSoftKeyboard(binder?.editText)
            }
        })
        binder?.button?.setOnClickListener(this@UpdateFragment)
    }

    override fun onClick(view: View?) {
        if (view == binder?.button) {
            binder?.getViewModel()?.updateItem(binder?.editText?.getText().toString())
            hideSoftKeyboard()
            dismiss()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkIfFragmentLoaded(this@UpdateFragment)
    }
}