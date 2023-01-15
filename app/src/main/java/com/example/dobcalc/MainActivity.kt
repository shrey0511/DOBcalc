package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null
    private var ageInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.button)
        selectedDate = findViewById(R.id.textView4)
        ageInMinutes = findViewById(R.id.textView5)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }

    }
    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _ , selectedYear , selectedMonth , selectedDayOfMonth ->
                Toast.makeText(this,
                    "DatePicker works!",Toast.LENGTH_LONG).show()
                val userDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                selectedDate?.text = userDate

                /*DatePickerDialog.OnDateSetListener{ _ , selectedYear , selectedMonth , selectedDayOfMonth ->
                Toast.makeText(this,
                "DatePicker works!",Toast.LENGTH_LONG).show()
                val userDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                selectedDate?.text = userDate
                */

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(userDate)
                theDate?.let{
                    val selectedDateInMins = theDate.time/60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMins

                        ageInMinutes?.text = differenceInMinutes.toString()

                    }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}//App is finished