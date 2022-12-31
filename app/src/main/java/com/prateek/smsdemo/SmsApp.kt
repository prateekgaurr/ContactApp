package com.prateek.smsdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SmsApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}