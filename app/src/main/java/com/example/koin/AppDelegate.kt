package com.example.koin

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.koin.module.APIModule
import com.example.koin.module.ApplicationModule
import com.example.koin.module.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppDelegate : MultiDexApplication() {

    companion object {
        var instance: AppDelegate? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
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
                    APIModule.module,
                    DataModule.module
                )
            )//module list
        }
    }
}