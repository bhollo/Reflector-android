package com.bhollo.reflector.ui.activities

import android.content.Context
import android.content.Intent
import android.content.res.Resources
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
    private var forSOS = false
    private val sosTimes = arrayOf(250L, 250L, 250L, 250L, 250L, 250L, 1000L, 250L, 1000L, 250L, 1000L, 250L, 250L, 250L, 250L, 250L, 250L, 1000L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(R.layout.activity_reflector)

        val extras = intent.extras
        color = extras?.getInt(Constants.COLOR)?: resources.getColor(R.color.bright_red)
        interval = extras?.getInt(Constants.INTERVAL)?: 500
        forSOS = extras?.getBoolean(Constants.FOR_SOS, false)?: false

        val brightness = 1.0f
        val lp = window.attributes
        lp.screenBrightness = brightness
        window.attributes = lp

        startReflector()
    }

    private fun startReflector() {
        Thread(Runnable {
            while (running) {
                if (forSOS){
                    runSOSSequence()
                }
                else{
                    runNormalSequence()
                }
            }
        })
            .start()
    }

    private fun runNormalSequence() {
        Thread.sleep(interval.toLong())
        runOnUiThread {
            reflectorImage.setBackgroundColor(color)
        }
        Thread.sleep(interval.toLong())
        runOnUiThread {
            reflectorImage.setBackgroundColor(resources.getColor(android.R.color.black))
        }
    }

    private fun runSOSSequence() {
        val backgroundToggle = BackgroundToggle(resources, color)
        for (item in sosTimes){
            runOnUiThread {
                val background = backgroundToggle.getBackground()
                reflectorImage.setBackgroundColor(background)
            }
            Thread.sleep(item)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        running = false
    }

    private class BackgroundToggle(resources: Resources, private val color: Int){

        private var currentBackground = -1
        private val black = resources.getColor(android.R.color.black)

        fun getBackground(): Int{
            return if(currentBackground == -1){
                currentBackground = color
                currentBackground
            } else{
                if (currentBackground == color){
                    currentBackground = black
                    black
                } else{
                    currentBackground = color
                    color
                }
            }
        }
    }

    companion object{

        fun getIntent(context: Context, color: Int, duration: Int, forSOS: Boolean = false): Intent{
            return Intent(context, ReflectorActivity::class.java)
                .apply {
                    putExtra(Constants.COLOR, color)
                    putExtra(Constants.INTERVAL, duration)
                    putExtra(Constants.FOR_SOS, forSOS)
                }
        }
    }
}