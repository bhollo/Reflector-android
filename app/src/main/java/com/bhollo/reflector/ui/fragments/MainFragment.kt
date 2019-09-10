package com.bhollo.reflector.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.bhollo.reflector.BuildConfig
import com.bhollo.reflector.R
import com.bhollo.reflector.databinding.MainFragmentBinding
import com.bhollo.reflector.extensions.inflateWithBinding
import com.bhollo.reflector.extensions.safeActivity
import com.bhollo.reflector.ui.activities.ReflectorActivity
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: Fragment() {

    private var currentColor = 0
    private var interval = 500

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = inflater.inflateWithBinding(R.layout.main_fragment, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentColor = resources.getColor(R.color.bright_red)

        radioColorGroup.setOnCheckedChangeListener(radioGroupListener)
        intervalSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener)

        binding.flavor = BuildConfig.FLAVOR

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