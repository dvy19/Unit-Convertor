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


        btnWeight.setOnClickListener {

            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)
        }


        val btnSpeed = findViewById<TextView>(R.id.speed)


        btnSpeed.setOnClickListener {

            val intent = Intent(this, SpeedActivity::class.java)
            startActivity(intent)
        }

        val btnTemp = findViewById<TextView>(R.id.temp_calc)


        btnTemp.setOnClickListener {

            val intent = Intent(this, tempActivity::class.java)
            startActivity(intent)
        }



        val btnLength = findViewById<TextView>(R.id.length_calc)


        btnLength.setOnClickListener {

            val intent = Intent(this, lengthActivity::class.java)
            startActivity(intent)

        val btnTime = findViewById<TextView>(R.id.time_calc)


        btnTime.setOnClickListener {
            // Create an Intent to navigate to SecondActivity
            val intent = Intent(this,timeActivity::class.java)
            startActivity(intent)
        }


        val btnPressure = findViewById<TextView>(R.id.pressure_calc)


        btnPressure.setOnClickListener {

            val intent = Intent(this,pressureActivity::class.java)
            startActivity(intent)
        }

        val btnStorage = findViewById<TextView>(R.id.storage_calc)
        btnStorage.setOnClickListener {

            val intent = Intent(this,storageActivity::class.java)
            startActivity(intent)
        }


            val btnArea = findViewById<TextView>(R.id.area_calc)


            btnArea.setOnClickListener {

                val intent = Intent(this,areaActivity::class.java)
                startActivity(intent)
            }
    }


}}