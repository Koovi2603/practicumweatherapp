
package com.example.practicumweatherapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {
//declare
    private lateinit var btnBack: Button
    private lateinit var tvDetails: TextView
    private lateinit var tvMinimumTemperature: TextView
    private lateinit var tvMaximumTemperature: TextView
    private lateinit var tvWeatherCondition: TextView
    private lateinit var tvAverageTemperature: TextView
    private lateinit var btnCalculateAverage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //initialise

        btnBack = findViewById(R.id.btnBack)
        tvDetails = findViewById(R.id.tvDetails)
        tvMinimumTemperature = findViewById(R.id.tvMinimumTemperature)
        tvMaximumTemperature = findViewById(R.id.tvMaximumTemperature)
        tvWeatherCondition = findViewById(R.id.tvWeatherCondition)
        tvAverageTemperature = findViewById(R.id.tvAverageTemperature)
        btnCalculateAverage = findViewById(R.id.btnCalculateAverage)

        val dayArray = intent.getFloatArrayExtra("dateArray")?.toList() ?: emptyList()
        val minimumTemperatureArray = intent.getFloatArrayExtra("timeArrayMorning")?.toList() ?: emptyList()
        val maximumTemperatureArray = intent.getFloatArrayExtra("timeArrayAfternoon")?.toList() ?: emptyList()
        val weatherConditionArray = intent.getStringArrayExtra("noteArray")?.toList() ?: emptyList()

        val dayBuilder = StringBuilder()
        for ((index, day) in dayArray.withIndex()) {
            dayBuilder.append("Day ${index + 1}: $day \n")
        }
        val minTempBuilder = StringBuilder()
        for ((index, minimumTemperature) in minimumTemperatureArray.withIndex()) {
            minTempBuilder.append("Minimum Temperature $index: $minimumTemperature \n")
        }
        val maxTempBuilder = StringBuilder()
        for ((index, maxTemperature) in maximumTemperatureArray.withIndex()) {
            maxTempBuilder.append("Maximum Temperature $index: $maxTemperature \n")
        }
        val weatherConditionBuilder = StringBuilder()
        for ((index, weatherCondition) in weatherConditionArray.withIndex()) {
            weatherConditionBuilder.append("Weather Condition $index: $weatherCondition \n")
        }

        tvDetails.text = dayBuilder.toString()
        tvMinimumTemperature.text = minTempBuilder.toString()
        tvMaximumTemperature.text = maxTempBuilder.toString()
        tvWeatherCondition.text = weatherConditionBuilder.toString()

        btnCalculateAverage.setOnClickListener {
            val averageTemperature = calculateAverage(minimumTemperatureArray, maximumTemperatureArray)
            tvAverageTemperature.text = "Average Temperature: $averageTemperature"
        }

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun calculateAverage(minimumTemperatures: List<Float>, maximumTemperatures: List<Float>): Float {
        var totalTemperature = 0f
        for (i in minimumTemperatures.indices) {
            totalTemperature += (minimumTemperatures[i] + maximumTemperatures[i]) / 2
        }
        return totalTemperature / minimumTemperatures.size
    }
}

