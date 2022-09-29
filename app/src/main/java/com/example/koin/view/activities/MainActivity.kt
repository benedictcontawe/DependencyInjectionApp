package com.example.koin.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.koin.R
import com.example.koin.databinding.MainBinder
import com.example.koin.view.models.MainViewModel
import com.example.koin.view.fragments.AddFragment
import com.example.koin.view.fragments.MainFragment
import com.example.koin.view.fragments.UpdateFragment
import com.example.koin.view.listeners.MainListener
import com.example.koin.view.models.MainAndroidViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.ext.android.viewModel

public class MainActivity : BaseActivity(), View.OnClickListener, MainListener {

    companion object {
        private val TAG : String = MainActivity::class.java.getSimpleName()

        public fun newIntent(context : Context) : Intent = Intent(context.getApplicationContext(), MainActivity::class.java)
        //.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    }

    private val standByDialog by lazy {
        val builder = this.let { AlertDialog.Builder(it) }
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val message = dialogView.findViewById<AppCompatTextView>(R.id.loadingText)
        message.setText( getString(R.string.Processing__please_wait_) )
        builder.setView(dialogView)
        builder.setCancelable(false)
        builder.create()
    }

    private var binder : MainBinder? = null
    private val viewModel : MainViewModel by viewModel<MainViewModel>()
    private val androidViewModel : MainAndroidViewModel by viewModel<MainAndroidViewModel>()

    override fun onCreate(savedInstanceState : Bundle?) {
        binder = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        super.onCreate(savedInstanceState)
        binder?.setViewModel(viewModel)
        binder?.setLifecycleOwner(this@MainActivity)
        if (savedInstanceState == null) {
            launchMain()
        }
    }

    override suspend fun onSetObservers(scope : CoroutineScope) {
        binder?.floatingActionButtonAdd?.setOnClickListener(this@MainActivity)
        binder?.floatingActionButtonDelete?.setOnClickListener(this@MainActivity)
        observeLoadState()
        getOnBackPressedDispatcher().addCallback(this@MainActivity, getHandleOnBackPressed())
    }

    private fun observeLoadState() {
        viewModel.observeLoadState().observe(this, object : Observer<Boolean> {
            override fun onChanged(isLoading : Boolean?) {
                when(isLoading == true) {
                    true -> standByDialog.show()
                    false -> standByDialog.dismiss()
                }
            }
        })
    }

    override fun onClick(view : View?) {
        if (view == binder?.floatingActionButtonAdd) launchAdd()
        else if (view == binder?.floatingActionButtonDelete) {
            viewModel.deleteAll()
            showToast(getString(R.string.delete_all))
        }
    }

    private fun replaceFragment(containerViewId : Int, fragment : Fragment) {
        getSupportFragmentManager().beginTransaction()
            .replace(containerViewId, fragment, fragment::class.java.getSimpleName())
            .commitNow()
    }

    private fun addToBackStackFragment(containerViewId : Int, fragment : Fragment) {
        if (getSupportFragmentManager().findFragmentByTag(fragment::class.java.getSimpleName()) == null)
            getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment, fragment::class.java.getSimpleName())
                .addToBackStack(fragment::class.java.getSimpleName())
                .commit()
    }

    private fun showDialogFragment(fragment : DialogFragment) {
        fragment.show(getSupportFragmentManager().beginTransaction(), fragment.javaClass.getName())
    }

    private fun launchMain() {
        replaceFragment(R.id.frame_layout, MainFragment.newInstance(this@MainActivity))
    }

    private fun launchAdd() {
        addToBackStackFragment(R.id.frame_layout, AddFragment.newInstance())
    }

    override fun launchUpdate() {
        showDialogFragment(UpdateFragment.newInstance())
    }
}