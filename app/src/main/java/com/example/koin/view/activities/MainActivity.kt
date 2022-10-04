package com.example.koin.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.koin.R
import com.example.koin.databinding.MainBinder
import com.example.koin.view.models.MainAndroidViewModel
import com.example.koin.view.models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
public class MainActivity : BaseActivity(), View.OnClickListener {

    companion object {
        private val TAG : String = MainActivity::class.java.getSimpleName()

        public fun newIntent(context : Context) : Intent = Intent(context.getApplicationContext(), MainActivity::class.java)
        //.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }

    private var binder : MainBinder? = null
    private val viewModel : MainViewModel by viewModels<MainViewModel>()
    private val androidViewModel : MainAndroidViewModel by viewModels<MainAndroidViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        super.onCreate(savedInstanceState)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@MainActivity)
    }

    override suspend fun onSetObservers(scope : CoroutineScope) {
        binder?.buttonBoolean?.setOnClickListener(this@MainActivity)
        binder?.buttonString?.setOnClickListener(this@MainActivity)
        binder?.buttonInteger?.setOnClickListener(this@MainActivity)
        binder?.buttonDouble?.setOnClickListener(this@MainActivity)
        binder?.buttonLong?.setOnClickListener(this@MainActivity)
        binder?.getViewModel()?.observeBoolean()?.observe(this, object : Observer<Boolean> {
            override fun onChanged(value : Boolean?) {
                logDebug(TAG,"observeBoolean() $value")
                binder?.labelBoolean?.setText(value.toString())
            }
        })

        binder?.getViewModel()?.observeString()?.observe(this, object : Observer<String> {
            override fun onChanged(value : String?) {
                logDebug(TAG,"observeString() $value")
                binder?.labelString?.setText(value.toString())
            }
        })

        binder?.getViewModel()?.observeInt()?.observe(this, object : Observer<Int> {
            override fun onChanged(value : Int?) {
                logDebug(TAG,"observeInt() $value")
                binder?.labelInteger?.setText(value.toString())
            }
        })

        binder?.getViewModel()?.observeDouble()?.observe(this, object : Observer<Double> {
            override fun onChanged(value : Double?) {
                logDebug(TAG,"observeInt() $value")
                binder?.labelDouble?.setText(value.toString())
            }
        })

        binder?.getViewModel()?.observeLong()?.observe(this, object : Observer<Long> {
            override fun onChanged(value : Long?) {
                logDebug(TAG,"observeInt() $value")
                binder?.labelLong?.setText(value.toString())
            }
        })
        getOnBackPressedDispatcher().addCallback(this@MainActivity, getHandleOnBackPressed())
    }

    override fun onClick(view : View) {
        when(view) {
            binder?.buttonBoolean -> {
                binder?.getViewModel()?.update(
                    binder?.checkBoxBoolean?.isChecked() ?: false
                )
            }
            binder?.buttonString -> {
                binder?.getViewModel()?.update(
                    binder?.editTextString?.getText().toString()
                )
            }
            binder?.buttonInteger -> {
                binder?.getViewModel()?.update(
                    binder?.editTextInteger?.getText().toString().toInt()
                )
            }
            binder?.buttonDouble -> {
                binder?.getViewModel()?.update(
                    binder?.editTextDouble?.getText().toString().toDouble()
                )
            }
            binder?.buttonLong -> {
                binder?.getViewModel()?.update(
                    binder?.editTextLong?.getText().toString().toLong()
                )
            }
        }
    }
}