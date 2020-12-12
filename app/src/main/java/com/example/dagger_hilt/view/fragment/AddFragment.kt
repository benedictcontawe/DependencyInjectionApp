package com.example.dagger_hilt.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dagger_hilt.MainViewModel
import com.example.dagger_hilt.R
import com.example.dagger_hilt.databinding.AddBinder
import com.example.dagger_hilt.model.CustomModel

class AddFragment : Fragment() {

    companion object {
        private val TAG : String = AddFragment.javaClass::class.java.getSimpleName()

        fun getTag() : String {
            return TAG
        }

        fun newInstance() : AddFragment {
            return AddFragment()
        }
    }

    private lateinit var binding : AddBinder
    private val mainAndroidViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.setMainViewModel(mainAndroidViewModel)
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.editText.requestFocus()
        showSoftKeyboard()

        binding.button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                binding.getMainViewModel()?.insertItem(CustomModel(binding.editText.text.toString()))
                hideSoftKeyboard()
                activity!!.supportFragmentManager.popBackStack()
            }
        })
    }

    private fun showSoftKeyboard() {
        val inputMethodManager : InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager : InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}