package com.example.koin.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.koin.databinding.UpdateBinder
import com.example.koin.MainViewModel
import com.example.koin.R
import com.example.koin.model.CustomModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UpdateFragment : BaseDialogFragment() {

    companion object {
        fun newInstance() : UpdateFragment =
            UpdateFragment()

        fun getTag() : String = "UpdateFragment"
    }

    private lateinit var binding : UpdateBinder
    private val viewModel : MainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.update_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProvider(activity!!).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getUpdate().observe(viewLifecycleOwner, object : Observer<CustomModel> {
            override fun onChanged(item : CustomModel) {
                binding.editText.setText(item.name)
                binding.editText.requestFocus()
                binding.editText.selectAll()
                showSoftKeyboard()
            }
        })

        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                viewModel.updateItem()
                hideSoftKeyboard()
                dismiss()
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

/*
AlertDialog.Builder(activity)
.setTitle("Update Name")
.setMessage("Please update your name")
.setView(getEditText())
.setIcon(R.drawable.ic_update_white)
.setPositiveButton("OK", object : DialogInterface.OnClickListener {
    override fun onClick(dialog : DialogInterface, which : Int) {
        dismiss()
    }
})
.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
    override fun onClick(dialog : DialogInterface?, which : Int) {
        dismiss()
    }
})
.create()
*/