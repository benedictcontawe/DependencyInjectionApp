package com.example.koin.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import com.example.koin.util.Coroutines

abstract class BaseDialogFragment : DialogFragment {

    companion object {
        private val TAG = BaseDialogFragment::class.java.getSimpleName()
    }

    constructor() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun getInputMethodManager() : InputMethodManager {
        return requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    protected fun showSoftKeyboard() {
        val inputMethodManager: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    protected fun showSoftKeyboard(view : View?) { Coroutines.main(this@BaseDialogFragment, work = {
        getInputMethodManager().showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    })
    }

    protected fun hideSoftKeyboard() { Coroutines.main(this@BaseDialogFragment, work = {
        getInputMethodManager().hideSoftInputFromWindow(requireView().windowToken, 0)
    }) }

    protected fun onBackPressed() {
        requireActivity().getSupportFragmentManager().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}