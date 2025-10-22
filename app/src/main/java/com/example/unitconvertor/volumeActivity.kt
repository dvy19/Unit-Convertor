package com.example.unitconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class volumeActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    private lateinit var resultText: TextView

    // Available speed units
    private val weightUnits = arrayOf("cm", "m")

    // Conversion rates to m/s (meters per second)
    private val conversionRates = mapOf(
        "cm" to 1.0000,
        "m" to 1000000.00000,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_calculation)


        inputNumber = findViewById(R.id.weight_input)
        fromSpinner = findViewById(R.id.weight_from)
        toSpinner = findViewById(R.id.weight_to)
        resultText = findViewById(R.id.output)


        setupSpinners()
        setupListeners()
    }

    private fun setupSpinners() {

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weightUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set same adapter to both dropdowns
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter


        fromSpinner.setSelection(0)
        toSpinner.setSelection(1)
    }

    private fun setupListeners() {

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


                resultText.text = "Result: ${"%.2f".format(result)} $toUnit"

            } catch (e: NumberFormatException) {
                resultText.text = "Please enter a valid number"
            }
        } else {
            resultText.text = "Enter a number above"
        }
    }
}