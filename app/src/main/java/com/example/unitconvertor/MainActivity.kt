package com.example.unitconvertor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homescreen)

        val btnWeight = findViewById<TextView>(R.id.weight)

        // Set click listener - what happens when button is clicked
        btnWeight.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent) // Start the new screen
        }


        val btnSpeed = findViewById<TextView>(R.id.speed)

        // Set click listener - what happens when button is clicked
        btnSpeed.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this, SpeedActivity::class.java)
            startActivity(intent) // Start the new screen
        }

        val btnTemp = findViewById<TextView>(R.id.temp_calc)

        // Set click listener - what happens when button is clicked
        btnTemp.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this, tempActivity::class.java)
            startActivity(intent) // Start the new screen
        }



        val btnLength = findViewById<TextView>(R.id.length_calc)

        // Set click listener - what happens when button is clicked
        btnLength.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this, lengthActivity::class.java)
            startActivity(intent) // Start the new screen
        }

        val btnTime = findViewById<TextView>(R.id.time_calc)

        // Set click listener - what happens when button is clicked
        btnTime.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this,timeActivity::class.java)
            startActivity(intent) // Start the new screen
        }


        val btnPressure = findViewById<TextView>(R.id.pressure_calc)

        // Set click listener - what happens when button is clicked
        btnPressure.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this,pressureActivity::class.java)
            startActivity(intent) // Start the new screen
        }
    }


}