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

class areaActivity : AppCompatActivity() {

    /* private means accessible only to this class.

    lateinit  = will be initialized later in the program
    here the variables are declared as global, can be used by any function present.
      */

    private lateinit var inputNumber: EditText    // first input
    private lateinit var outputNumber: EditText   // 2nd input
    private lateinit var fromSpinner: Spinner       // 1st dropdown
    private lateinit var toSpinner: Spinner         // 2nd dropdown


    //defining an array of  units which is to  be set in teh dropdown later
    private val areaUnits = arrayOf("cm", "m", "km")

    // how many of my base units are in one of this unit.
    //creating a map for conversion logic easier
    private val conversionRates = mapOf(
        "cm" to 1.0,
        "m" to 1.0e4,
        "km" to 1.0e6,
    )

    // To prevent infinite loop when programmatically setting text,
    /*
    it checks only one time conversion while entering the input.

    suppose 1st field has 10m, which is converted to 100cm in next output input field.
    as output field has also listeners attached, so it will again convert 100 to 10 and again 10 to 100.
    so conversion will take place infinitely.
     */
    private var isConvertingFromInput = false
    private var isConvertingFromOutput = false


    //over riding the function onCreate provided by app compat activity interface
    // savedInstanceState saves the pre stored data or state of app.
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //setting the view to be displayed for this activity, area.xml
        setContentView(R.layout.area)

        //fetching the views from UI to work on, type of view is already declared
        inputNumber = findViewById(R.id.area_input)
        outputNumber = findViewById(R.id.area_output)
        fromSpinner = findViewById(R.id.area_from)
        toSpinner = findViewById(R.id.area_to)


        //making an on-click function for backBtn
        val backBtn = findViewById<TextView>(R.id.back_btn)
        backBtn.setOnClickListener {

            //Intent is an object like a taxi  helps in navigate/deliver to the next screen
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setupSpinners()
        setupListeners()
    }

    private fun setupSpinners() {


        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, areaUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set same adapter to both dropdowns
        fromSpinner.adapter = adapter
        toSpinner.adapter = adapter

        fromSpinner.setSelection(0)
        toSpinner.setSelection(1)
    }

    private fun setupListeners() {
        // Listener for first input field (area_input)
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