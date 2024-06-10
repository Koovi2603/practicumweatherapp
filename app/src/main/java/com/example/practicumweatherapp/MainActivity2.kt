package com.example.practicumweatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException

class MainActivity2 : AppCompatActivity() {

    //Declaration

    private lateinit var etDay: EditText
    private lateinit var etMinimumTemperature: EditText
    private lateinit var etMaximumTemperature: EditText
    private lateinit var etWeatherCondition: EditText
    private lateinit var tvMessage: TextView

    private lateinit var buttonSave: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonNext: Button

    private val dayArray = mutableListOf<Float>()
    private val minimumTemperatureArray = mutableListOf<Float>()
    private val maximumTemperatureArray = mutableListOf<Float>()
    private val weatherConditionArray = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        buttonClear = findViewById(R.id.buttonClear)
        buttonNext = findViewById(R.id.buttonNext)
        etDay = findViewById(R.id.etDay)
        etMinimumTemperature = findViewById(R.id.etMinimumTemperature)
        etMaximumTemperature = findViewById(R.id.etMaximumTemperature)
        etWeatherCondition = findViewById(R.id.etWeatherCondition)
        buttonSave = findViewById(R.id.buttonSave)
        tvMessage = findViewById(R.id.tvMessage)

        buttonClear.setOnClickListener {
            clearEditTexts()
        }

        buttonSave.setOnClickListener {
            val day = etDay.text.toString()
            val minimumTemperature = etMinimumTemperature.text.toString()
            val maximumTemperature = etMaximumTemperature.text.toString()
            val weatherCondition = etWeatherCondition.text.toString()

            if (day.isNotEmpty() && minimumTemperature.isNotEmpty() && maximumTemperature.isNotEmpty()) {
                try {
                    dayArray.add(day.toFloat())
                    minimumTemperatureArray.add(minimumTemperature.toFloat())
                    maximumTemperatureArray.add(maximumTemperature.toFloat())
                    weatherConditionArray.add(weatherCondition)
                    clearEditTexts()

                } catch (e: NumberFormatException) {
                    tvMessage.text = "Please enter a valid number"
                }
            } else {
                tvMessage.text = "Input cannot be empty"
            }
        }

        buttonNext.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("dateArray", dayArray.toFloatArray())
            intent.putExtra("timeArrayMorning", minimumTemperatureArray.toFloatArray())
            intent.putExtra("timeArrayAfternoon", maximumTemperatureArray.toFloatArray())
            intent.putExtra("noteArray", weatherConditionArray.toTypedArray())
            startActivity(intent)
        }
    }

    private fun clearEditTexts() {
        etDay.text.clear()
        etMinimumTemperature.text.clear()
        etMaximumTemperature.text.clear()
        etWeatherCondition.text.clear()
    }
}

