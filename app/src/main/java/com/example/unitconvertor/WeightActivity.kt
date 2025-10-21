package com.example.unitconvertor


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class WeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weight_calculation) // Links to activity_second.xml


        val weightInput=findViewById<EditText>(R.id.weight_input)
        val convertBtn=findViewById<Button>(R.id.convert_btn)
        val weightFrom=findViewById<Spinner>(R.id.weight_from)
        val weightTo=findViewById<Spinner>(R.id.weight_to)

        val weightOutput=findViewById<TextView>(R.id.output)


        val weight = arrayOf("Grams","Kilograms")
        val  adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,weight)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        weightFrom.adapter=adapter
        weightTo.adapter=adapter


        convertBtn.setOnClickListener(){

            val input=weightInput.text.toString()

            var convertedWeight: Double
            var from: String
            var to:String


            if(input.isEmpty())
                weightOutput.text="Please enter a number"

            else{


                val weight=input.toDouble()
                val from_option=weightFrom.selectedItem.toString()

                val to_option=weightTo.selectedItem.toString()


                if(from_option=="Grams" && to_option=="Kilograms"){
                    convertedWeight=weight/1000
                    from="Grams"
                    to="Kilograms"
                }

                else{
                    convertedWeight=weight*1000
                    from="Kilograms"
                    to="Grams"
                }

                weightOutput.text = "$weight $from  = $convertedWeight $to"

            }



        }



        // Find the button from XML

    }
}