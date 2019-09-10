package com.bhollo.reflector

import android.app.Application
import com.bhollo.reflector.extensions.isFreeVersion
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.testfairy.TestFairy

class ReflectorApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TestFairy.begin(this, "SDK-eTDNMh8y")
        if (isFreeVersion()) {
            MobileAds.initialize(this)
        }
    }
}