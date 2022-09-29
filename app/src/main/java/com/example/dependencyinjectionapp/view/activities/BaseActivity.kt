package com.example.dependencyinjectionapp.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.dependencyinjectionapp.util.Coroutines
import com.example.dependencyinjectionapp.view.fragments.BaseBottomSheetDialogFragment
import com.example.dependencyinjectionapp.view.fragments.BaseDialogFragment
import com.example.dependencyinjectionapp.view.fragments.BaseFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseActivity : AppCompatActivity {

    companion object {
        private val TAG = BaseActivity::class.java.getSimpleName()
    }

    constructor() {

    }

    protected fun logDebug(TAG : String, message : String) {
        Log.d(TAG,message)
    }

    protected fun showToast(messageToast : String?, duration : Int = Toast.LENGTH_SHORT) { logDebug(TAG, "showToast($messageToast)")
        Coroutines.main(this@BaseActivity, { scope -> scope.launch( block = {
            Toast.makeText(this@BaseActivity, messageToast, duration).show()
        }) })
    }

    protected fun showSnackBar(view : View, messageToast : String, duration : Int) { logDebug(TAG, "showSnackBar($messageToast)")
        Coroutines.main(this@BaseActivity, { scope -> scope.launch( block = {
            Snackbar.make(view, messageToast, duration).show()
        }) })
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        Coroutines.main(this@BaseActivity, { onSetObservers(it) })
    }

    protected abstract suspend fun onSetObservers(scope : CoroutineScope)
    //region Fragment Manager Methods
    protected fun replaceFragment(containerViewId : Int, fragment: BaseFragment) {
        logDebug(TAG, "replaceFragment($containerViewId,$fragment)")
        getSupportFragmentManager().beginTransaction()
            .replace(containerViewId, fragment, fragment::class.java.getSimpleName())
            .commitNow()
    }

    protected fun replaceFragment(containerViewId : Int, fragment: BaseFragment, enter : Int, exit : Int) {
        logDebug(TAG, "replaceFragment($containerViewId,$fragment)")
        getSupportFragmentManager().beginTransaction()
            .setCustomAnimations(enter, exit)
            .replace(containerViewId, fragment, fragment::class.java.getSimpleName())
            .commitNow()
    }

    protected fun addToBackStackFragment(containerViewId : Int, fragment : BaseFragment) {
        logDebug(TAG, "addToBackStackFragment($containerViewId,$fragment)")
        if (getSupportFragmentManager().findFragmentByTag(fragment::class.java.getSimpleName()) == null)
            getSupportFragmentManager().beginTransaction()
                .add(containerViewId, fragment, fragment::class.java.getSimpleName())
                .addToBackStack(fragment::class.java.getSimpleName())
                .commit()
    }

    protected fun showDialogFragment(fragment : BaseDialogFragment) {
        fragment.show(getSupportFragmentManager().beginTransaction(), fragment.javaClass.getName())
    }

    protected fun showBottomSheetFragment(bottomSheetDialogFragment : BaseBottomSheetDialogFragment) {
        bottomSheetDialogFragment.setCancelable(true);
        bottomSheetDialogFragment.show(getSupportFragmentManager(),bottomSheetDialogFragment.javaClass.getName())
    }

    protected fun popBackStack() { logDebug(TAG, "popBackStack()")
        if (getSupportFragmentManager().getFragments().isNotEmpty()) getSupportFragmentManager().popBackStack()
    }

    protected fun popBackStack(pops : Int) { logDebug(TAG, "popBackStack($pops)")
        if(getSupportFragmentManager().getFragments().isNotEmpty() && pops <= 1) popBackStack()
        else if (getSupportFragmentManager().getFragments().isNotEmpty()) for (i in 0 until pops ) { logDebug(TAG, "popBackStack i $i")
            getSupportFragmentManager().popBackStack()
        }
    }

    protected fun popAllBackStack() { logDebug(TAG, "popAllBackStack()")
        if (getSupportFragmentManager().getFragments().isNotEmpty()) //getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().getFragments().map { fragment -> getSupportFragmentManager().popBackStack(fragment.getTag(), FragmentManager.POP_BACK_STACK_INCLUSIVE) }
    }
    //endregion
    //region onBackPressed Methods
    protected fun getHandleOnBackPressed() : OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { logDebug(TAG,"handleOnBackPressed()")
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish()
                } else getSupportFragmentManager().popBackStackImmediate()
            }
        }
    }

    protected fun getHandleOnBackPressed(drawerLayout : DrawerLayout, navigationView : NavigationView) : OnBackPressedCallback {
        return object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { logDebug(TAG,"handleOnBackPressed()")
                if (drawerLayout.isDrawerOpen(navigationView)) { logDebug(TAG,"closeDrawer()")
                    drawerLayout.closeDrawer(navigationView, true)
                } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    finish()
                } else getSupportFragmentManager().popBackStackImmediate()
            }
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("Use this method getHandleOnBackPressed"), DeprecationLevel.WARNING)
    override fun onBackPressed() { Log.d(TAG,"onBackPressed()")
        if (getOnBackPressedDispatcher().hasEnabledCallbacks())
            super.onBackPressed()
        else if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed()
        else
            getSupportFragmentManager().popBackStack()
    }

    protected fun onBackPressed(drawerLayout : DrawerLayout, navigationView : NavigationView) {
        if (drawerLayout.isDrawerOpen(navigationView)) { logDebug(TAG,"closeDrawer()")
            drawerLayout.closeDrawer(navigationView, true)
        } else { logDebug(TAG,"super.onBackPressed()")
            super.onBackPressed()
        }
    }
    //endregion
    //region Leave App Method
    protected fun exitApp() { logDebug(TAG,"exitApp()")
        ActivityCompat.finishAffinity(this@BaseActivity)
        System.exit(0)
    }
    //endregion
    override fun onDestroy() { logDebug(TAG,"onDestroy()")
        super.onDestroy()
    }
}