package com.bhollo.reflector.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bhollo.reflector.R
import com.bhollo.reflector.extensions.inflateTo
import com.bhollo.reflector.extensions.safeActivity
import com.bhollo.reflector.ui.activities.ReflectorActivity
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: Fragment() {

    private var currentColor = 0
    private var interval = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.main_fragment, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startButton.setOnClickListener {
            val activity = ReflectorActivity.getIntent(safeActivity, currentColor, interval)
            startActivity(activity)
        }

        ArrayAdapter.createFromResource(
            safeActivity,
            R.array.colors,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            colorSpinner.adapter = adapter

        }

        ArrayAdapter.createFromResource(
            safeActivity,
            R.array.intervals,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            intervalSpinner.adapter = adapter
        }

        colorSpinner.onItemSelectedListener = listener
        intervalSpinner.onItemSelectedListener = listener
    }

    private val listener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(adapter: AdapterView<*>?) {

        }

        override fun onItemSelected(adapter: AdapterView<*>?, view: View?, postition: Int, id: Long) {
            view?: return
            adapter?: return
            val item = adapter.selectedItem as String
            val id = adapter.id
            when(id){
                R.id.colorSpinner ->{
                    when(item){
                        "blue" -> {
                           currentColor = resources.getColor(R.color.bright_blue)
                        }

                        "green" ->{
                            currentColor = resources.getColor(R.color.bright_green)
                        }
                        "yellow" -> {
                            currentColor = resources.getColor(R.color.bright_yellow)
                        }

                        "red" ->{
                            currentColor = resources.getColor(R.color.bright_red)
                        }
                    }
                }

                R.id.intervalSpinner -> {
                    interval = item.toInt()
                }
            }
            Log.d("test", item)
        }

    }
}