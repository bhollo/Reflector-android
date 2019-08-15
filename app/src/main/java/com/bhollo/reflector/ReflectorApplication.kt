package com.bhollo.reflector

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.testfairy.TestFairy

class ReflectorApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TestFairy.begin(this, "SDK-eTDNMh8y")
        MobileAds.initialize(this)
    }
}