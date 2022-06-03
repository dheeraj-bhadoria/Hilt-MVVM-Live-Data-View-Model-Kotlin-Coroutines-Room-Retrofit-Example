package com.app.postdemo.Application

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    companion object{
        private var context: Context? = null

        fun getAppContext(): Context? {
            return MyApplication.context
        }
    }

    override fun onCreate() {
        super.onCreate()
        MyApplication.context = applicationContext
    }

}