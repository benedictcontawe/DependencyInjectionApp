package com.example.koin

import androidx.multidex.MultiDexApplication
import com.example.koin.module.NetworkModule
import com.example.koin.module.ApplicationModule
import com.example.koin.module.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppDelegate : MultiDexApplication() {

    companion object {
        private val TAG : String = AppDelegate::class.java.getSimpleName()
    }

    init {

    }

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()  // use AndroidLogger as Koin Logger - default Level.INFO
            androidContext(this@AppDelegate) // use the Android context given there
            androidFileProperties() // load properties from assets/koin.properties file
            this.koin.loadModules(
                listOf(
                    ApplicationModule.module,
                    NetworkModule.module,
                    DataModule.module
                )
            )//module list
        }
    }
}