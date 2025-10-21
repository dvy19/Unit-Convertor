package com.example.unitconvertor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homescreen)

        val btnWeight = findViewById<Button>(R.id.weight)

        // Set click listener - what happens when button is clicked
        btnWeight.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent) // Start the new screen
        }
    }


}