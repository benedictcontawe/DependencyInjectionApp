package com.example.koin.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.koin.util.Coroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment {

    companion object {
        private val TAG = BaseFragment::class.java.getSimpleName()
    }

    constructor() {

    }

    protected fun logDebug(TAG : String, message : String) {
        Log.d(TAG,message)
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

    protected fun showSoftKeyboard(view : View?) { Coroutines.main(this@BaseFragment, work = {
        getInputMethodManager().showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    })
    }

    protected fun hideSoftKeyboard() { Coroutines.main(this@BaseFragment, work = {
        getInputMethodManager().hideSoftInputFromWindow(requireView().windowToken, 0)
    }) }

    protected fun onBackPressed() {
        requireActivity().getSupportFragmentManager().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}