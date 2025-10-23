package com.example.unitconvertor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

import android.widget.Button

class SpeedActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var resultText: TextView

    // Available speed units
    private val speedUnits = arrayOf("m/s", "km/h", "mph", "ft/s")

    // Conversion rates to m/s (meters per second)
    private val conversionRates = mapOf(
        "m/s" to 1.0,
        "km/h" to 0.277778,
        "mph" to 0.44704,
        "ft/s" to 0.3048
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.speed_calculation)


        inputNumber = findViewById(R.id.speed_input)
        fromSpinner = findViewById(R.id.speed_from)
        toSpinner = findViewById(R.id.speed_to)
        resultText = findViewById(R.id.output)

        val backBtn = findViewById<Button>(R.id.back_btn)
        backBtn.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        setupSpinners()


        setupListeners()
    }

    private fun setupSpinners() {
        // Create adapter for dropdowns
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, speedUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set same adapter to both dropdowns
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        // Set default selections
        fromSpinner.setSelection(0) // m/s

        toSpinner.setSelection(1)   // km/h
    }

    private fun setupListeners() {
        // Listen for text changes in input field
        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                calculateResult()
            }
        })

        // Listen for selection changes in both dropdowns
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                calculateResult()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        fromSpinner.onItemSelectedListener = spinnerListener
        toSpinner.onItemSelectedListener = spinnerListener
    }

    private fun calculateResult() {
        val inputText = inputNumber.text.toString()

        // Check if input is not empty and is a valid number
        if (inputText.isNotEmpty()) {
            try {
                val inputValue = inputText.toDouble()
                val fromUnit = fromSpinner.selectedItem.toString()
                val toUnit = toSpinner.selectedItem.toString()


                val valueInBase = inputValue * conversionRates[fromUnit]!!
                val result = valueInBase / conversionRates[toUnit]!!

                // Display result with 2 decimal places
                resultText.text = "Result: ${"%.2f".format(result)} $toUnit"

            } catch (e: NumberFormatException) {
                resultText.text = "Please enter a valid number"
            }
        } else {
            resultText.text = "Enter a number above"
        }
    }
}