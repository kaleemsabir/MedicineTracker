package com.example.medicinetrackerapp

import android.app.Application
import com.example.medicinetrackerapp.data.AppCacheData
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var appCacheData: AppCacheData


    private val TAG = "MyApp"
    override fun onCreate() {
        super.onCreate()
        instance =  this
    }

    companion object {
        lateinit var instance: MyApp
    }
}