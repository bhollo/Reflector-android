package com.bhollo.reflector.extensions

import com.bhollo.reflector.BuildConfig

fun isFreeVersion(): Boolean{
    return BuildConfig.FLAVOR.contentEquals("free")
}