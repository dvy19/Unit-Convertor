package com.example.unitconvertor


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight) // Links to activity_second.xml

        // Find the button from XML
        val btnGoToScreen1 = findViewById<Button>(R.id.GoToMain)

        // Set click listener
        btnGoToScreen1.setOnClickListener {
            // Create an Intent to navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Start the main screen
        }
    }
}