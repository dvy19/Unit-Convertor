package com.example.unitconvertor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homescreen)

        val btnWeight = findViewById<LinearLayout>(R.id.weight)
        btnWeight.setOnClickListener {
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)
            finish()
        }


        val btnSpeed = findViewById<LinearLayout>(R.id.speed_calc)
        btnSpeed.setOnClickListener {

            val intent = Intent(this, SpeedActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnVolume = findViewById<LinearLayout>(R.id.volume_calc)
        btnVolume.setOnClickListener {

            val intent = Intent(this, volumeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnStorage = findViewById<LinearLayout>(R.id.storage_calc)
        btnStorage.setOnClickListener {

            val intent = Intent(this, storageActivity::class.java)
            startActivity(intent)
            finish()
        }


        val btnTemp = findViewById<LinearLayout>(R.id.temp_calc)
        btnTemp.setOnClickListener {

            val intent = Intent(this, tempActivity::class.java)
            startActivity(intent)
            finish()
        }


        val btnLength = findViewById<LinearLayout>(R.id.length_calc)
        btnLength.setOnClickListener {

            val intent = Intent(this, lengthActivity::class.java)
            startActivity(intent)
            finish()

        val btnTime = findViewById<LinearLayout>(R.id.time_calc)

        btnTime.setOnClickListener {

            val intent = Intent(this,timeActivity::class.java)
            startActivity(intent)
            finish()
        }


        val btnPressure = findViewById<LinearLayout>(R.id.pressure_calc)
        btnPressure.setOnClickListener {

            val intent = Intent(this,pressureActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnStorage = findViewById<LinearLayout>(R.id.storage_calc)
        btnStorage.setOnClickListener {

            val intent = Intent(this,storageActivity::class.java)
            startActivity(intent)
            finish()
        }



    }


        val btnTime = findViewById<LinearLayout>(R.id.time_calc)
        btnTime.setOnClickListener {

            val intent = Intent(this, timeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnArea = findViewById<LinearLayout>(R.id.area_calc)
        btnArea.setOnClickListener {

            val intent = Intent(this, areaActivity::class.java)
            startActivity(intent)
            finish()
        }



}}