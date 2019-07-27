package com.bhollo.reflector.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.bhollo.reflector.R
import com.bhollo.reflector.constants.Constants
import kotlinx.android.synthetic.main.activity_reflector.*

class ReflectorActivity: AppCompatActivity() {

    private var running = true

    private var interval = 500
    private var color: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(R.layout.activity_reflector)

        val extras = intent.extras
        color = extras?.getInt(Constants.COLOR)?: resources.getColor(R.color.bright_red)
        interval = extras?.getInt(Constants.duration)?: 500

        val brightness = 1.0f
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp

        Thread(Runnable {
            while (running) {
                Thread.sleep(interval.toLong())
                runOnUiThread {
                    reflectorImage.setBackgroundColor(color)
                }
                Thread.sleep(interval.toLong())
                runOnUiThread {
                    reflectorImage.setBackgroundColor(resources.getColor(android.R.color.black))
                }
            }
        })
            .start()
    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
    }

    companion object{

        fun getIntent(context: Context, color: Int, duration: Int): Intent{
            return Intent(context, ReflectorActivity::class.java)
                .apply {
                    putExtra(Constants.COLOR, color)
                    putExtra(Constants.duration, duration)
                }
        }
    }
}