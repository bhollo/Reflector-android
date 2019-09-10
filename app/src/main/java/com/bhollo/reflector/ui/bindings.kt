package com.bhollo.reflector.ui

import androidx.databinding.BindingAdapter
import com.bhollo.reflector.extensions.hide
import com.bhollo.reflector.extensions.show
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

@BindingAdapter("flavor")
fun setupAd(view: AdView, flavor: String){

    if (flavor.contentEquals("free")){
        val adRequest = AdRequest
            .Builder()
           // .addTestDevice("5E32C5BA282ED1BC45704B9F4E8D3FFD")
            .build()
        view.loadAd(adRequest)
        view.show()
        return
    }
    view.hide()
}