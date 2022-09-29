package com.example.dependencyinjectionapp

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
/**
 * An application with @HiltAndroidApp that triggers Hilt's code generation and
 * adds an application-level dependency container.
 */
@HiltAndroidApp
class AppDelegate : MultiDexApplication() {

    companion object {
        private val TAG : String = AppDelegate::class.java.getSimpleName()
    }

    init {

    }
}