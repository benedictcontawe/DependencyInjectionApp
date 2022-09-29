package com.example.dependencyinjectionapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/**
 * An application with @HiltAndroidApp that triggers Hilt's code generation and
 * adds an application-level dependency container.
 */
@HiltAndroidApp
class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}