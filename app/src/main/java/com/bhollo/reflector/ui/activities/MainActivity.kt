package com.bhollo.reflector.ui.activities

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.bhollo.reflector.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var loadingAnimation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(R.layout.activity_main)

        val brightness = 1.0f
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp

        reflectorImage.apply {
            setBackgroundResource(R.drawable.reflector)
            loadingAnimation = background as AnimationDrawable
        }
    }

    override fun onStart() {
        super.onStart()
        loadingAnimation.start()
    }
}
