package com.example.koin

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.koin.dependency.module.APIModule
import com.example.koin.dependency.module.ApplicationModule
import com.example.koin.dependency.module.DataModule
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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        //for below SDK 20
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()


        // Start Koin
        startKoin{
            androidLogger()  // use AndroidLogger as Koin Logger - default Level.INFO
            androidContext(this@AppDelegate) // use the Android context given there
            androidFileProperties() // load properties from assets/koin.properties file
            modules(
                listOf(
                    ApplicationModule.applicationModule,
                    APIModule.apiModule,
                    DataModule.dataModule
                )
            ) // module list
        }
    }


}