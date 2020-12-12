package com.example.dagger_hilt.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.example.dagger_hilt.MainAndroidViewModel
import com.example.dagger_hilt.MainViewModel
import com.example.dagger_hilt.R
import com.example.dagger_hilt.databinding.MainBinder
import com.example.dagger_hilt.view.fragment.AddFragment
import com.example.dagger_hilt.view.fragment.MainFragment
import com.example.dagger_hilt.view.fragment.UpdateFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG : String = MainActivity::class.java.simpleName
    }

    private val mainViewModel : MainViewModel by viewModels()
    private val androidViewModel : MainAndroidViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            callMainFragment()
        }
        Log.d(TAG,"MainViewModel Instance ${mainViewModel?.getInstance()}")
        Log.d(TAG,"MainAndroidViewModel Instance ${androidViewModel?.getInstance()}")
        Log.d(TAG,"MainViewModel Repository Instance ${mainViewModel?.getRepositoryInstance()}")
        Log.d(TAG,"MainAndroidViewModel Repository Instance ${androidViewModel?.getRepositoryInstance()}")
    }

    //region Observer Methods
    private fun callMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    fun callAddFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, AddFragment.newInstance())
            .addToBackStack(
                AddFragment.getTag())
            .commit()
    }

    fun callUpdateFragment() {
        UpdateFragment
            .newInstance()
            .show(
                supportFragmentManager.beginTransaction(),
                UpdateFragment.getTag()
            )
    }

    fun showSoftKeyboard(activity : Activity, showKeyboard : Boolean) {
        var view = activity.currentFocus
        when(showKeyboard){
            true -> {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
            false ->{
                val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                //Find the currently focused view, so we can grab the correct window token from it.

                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = View(activity)
                }
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        }
        else {
            supportFragmentManager.popBackStack()
        }
    }
}