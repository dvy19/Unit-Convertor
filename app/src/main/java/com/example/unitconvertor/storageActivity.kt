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

class storageActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var outputNumber: EditText
    private lateinit var fromSpinner: Spinner
    private lateinit var toSpinner: Spinner
    //private lateinit var resultText: TextView

    // Available speed units
    private val storageUnits = arrayOf("bit", "byte", "kb","mb","gb")

    // how many of my base units are in one of this unit.
    private val conversionRates = mapOf(
        "bit" to 1.0,
        "byte" to 8.0,
        "kb" to 1.0e3,
        "mb" to 1.0e6,
        "gb" to 1.0e9
    )

    // To prevent infinite loop when programmatically setting text
    private var isConvertingFromInput = false
    private var isConvertingFromOutput = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.storage)

        inputNumber = findViewById(R.id.storage_input)
        outputNumber = findViewById(R.id.storage_output) // Assuming this is your second input field
        fromSpinner = findViewById(R.id.storage_from)
        toSpinner = findViewById(R.id.storage_to)
        //resultText = findViewById(R.id.output)

        val backBtn = findViewById<TextView>(R.id.back_btn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupSpinners()
        setupListeners()
    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, storageUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set same adapter to both dropdowns
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        fromSpinner.setSelection(0)
        toSpinner.setSelection(1)
    }

    private fun setupListeners() {
        // Listener for first input field (storage_input)
        inputNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!isConvertingFromOutput) {
                    isConvertingFromInput = true
                    convertFromInputToOutput()
                    isConvertingFromInput = false
                }
            }
        })

        // Listener for second input field (len_output)
        outputNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (!isConvertingFromInput) {
                    isConvertingFromOutput = true
                    convertFromOutputToInput()
                    isConvertingFromOutput = false
                }
            }
        })

        // Listen for selection changes in both dropdowns
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                // When spinner changes, convert based on which field has content
                when {
                    inputNumber.text.isNotEmpty() -> convertFromInputToOutput()
                    outputNumber.text.isNotEmpty() -> convertFromOutputToInput()
                    //else -> resultText.text = "Enter a number in any field"
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        fromSpinner.onItemSelectedListener = spinnerListener
        toSpinner.onItemSelectedListener = spinnerListener
    }

    private fun convertFromInputToOutput() {
        val inputText = inputNumber.text.toString()

        if (inputText.isNotEmpty()) {
            try {
                val inputValue = inputText.toDouble()
                val fromUnit = fromSpinner.selectedItem.toString()
                val toUnit = toSpinner.selectedItem.toString()

                val valueInBase = inputValue * conversionRates[fromUnit]!!
                val result = valueInBase / conversionRates[toUnit]!!

                // Update the output field
                outputNumber.setText("%.2f".format(result))
                //resultText.text = "Conversion: ${"%.2f".format(inputValue)} $fromUnit = ${"%.4f".format(result)} $toUnit"

            } catch (e: NumberFormatException) {
                //resultText.text = "Please enter a valid number"
                outputNumber.text.clear()
            }
        } else {
            //resultText.text = "Enter a number in any field"
            outputNumber.text.clear()
        }
    }

    private fun convertFromOutputToInput() {
        val outputText = outputNumber.text.toString()

        if (outputText.isNotEmpty()) {
            try {
                val outputValue = outputText.toDouble()
                val fromUnit = fromSpinner.selectedItem.toString()
                val toUnit = toSpinner.selectedItem.toString()

                // Reverse conversion: output is in "toUnit", convert back to "fromUnit"
                val valueInBase = outputValue * conversionRates[toUnit]!!
                val result = valueInBase / conversionRates[fromUnit]!!

                // Update the input field
                inputNumber.setText("%.2f".format(result))
                //resultText.text = "Conversion: ${"%.2f".format(outputValue)} $toUnit = ${"%.4f".format(result)} $fromUnit"

            } catch (e: NumberFormatException) {
                //resultText.text = "Please enter a valid number"
                inputNumber.text.clear()
            }
        } else {
            //resultText.text = "Enter a number in any field"
            inputNumber.text.clear()
        }
    }
}