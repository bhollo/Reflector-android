package com.bhollo.reflector

import android.app.Application
import com.bhollo.reflector.extensions.isFreeVersion
import com.google.android.gms.ads.MobileAds

class ReflectorApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (isFreeVersion()) {
            MobileAds.initialize(this)
        }
    }
}