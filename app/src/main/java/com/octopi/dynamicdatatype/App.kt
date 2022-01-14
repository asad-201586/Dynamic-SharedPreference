package com.octopi.dynamicdatatype

import android.app.Application

class App: Application() {

    val sharedPreferenceUtils: SharedPreferenceUtils by lazy { SharedPreferenceUtils(this) }

    override fun onCreate() {
        super.onCreate()
        application = this

    }

    companion object{
        lateinit var application: App
        @JvmStatic
        fun getApp() = application
    }
}

fun getPrefs(): SharedPreferenceUtils{
    return App.getApp().sharedPreferenceUtils
}