package com.bhollo.reflector.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhollo.reflector.R
import com.bhollo.reflector.extensions.replaceFragment
import com.bhollo.reflector.ui.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        replaceFragment(fragment = MainFragment())
    }
}
