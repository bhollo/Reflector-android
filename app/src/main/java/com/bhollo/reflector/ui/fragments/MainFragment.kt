package com.bhollo.reflector.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.bhollo.reflector.R
import com.bhollo.reflector.extensions.inflateTo
import com.bhollo.reflector.extensions.safeActivity
import com.bhollo.reflector.ui.activities.ReflectorActivity
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: Fragment() {

    private var currentColor = 0
    private var interval = 500

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.main_fragment, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentColor = resources.getColor(R.color.bright_red)

        radioColorGroup.setOnCheckedChangeListener(radioGroupListener)
        intervalSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener)

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        startButton.setOnClickListener {
            val activity = ReflectorActivity.getIntent(safeActivity, currentColor, interval)
            startActivity(activity)
        }
    }

    private val radioGroupListener = RadioGroup.OnCheckedChangeListener { group, checkedId ->

        when(checkedId){

            R.id.radioButtonRed -> {
                currentColor = resources.getColor(R.color.bright_red)
            }

            R.id.radioButtonBlue -> {
                currentColor = resources.getColor(R.color.bright_blue)
            }

            R.id.radioButtonGreen -> {
                currentColor = resources.getColor(R.color.bright_green)
            }

            R.id.RadioButtonYellow -> {
                currentColor = resources.getColor(R.color.bright_yellow)
            }
        }
    }

    private val onSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {

        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (progress <= 0){
                interval = 500
                return
            }
            interval = 500/progress
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {}

        override fun onStopTrackingTouch(seekBar: SeekBar?) {}

    }
}