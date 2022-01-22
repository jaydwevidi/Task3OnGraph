package com.example.task3ongraph

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.task3ongraph.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var pronounText :  String
    private lateinit var datepickerListner : DatePickerDialog.OnDateSetListener
    private lateinit var timePickerListner : TimePickerDialog.OnTimeSetListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAutocompleteTV()
        setupSpinnerClickListner()
        setupSpinnerEntriesProgramatically()





    }

    fun setupTimePickerListner(){
        timePickerListner = object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                Toast.makeText(this@MainActivity, " time = $p1:$p2", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showTimePicker(){
        val timePicker = TimePickerDialog(this , timePickerListner ,  0 , 0 , false)
        timePicker.show()
    }

    fun setupDatePickerListner(){
        datepickerListner = object  : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                Toast.makeText(this@MainActivity, "$p1 $p2 $p3", Toast.LENGTH_SHORT).show()
            }
        }
        //datepickerListner = DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 -> TODO("Not yet implemented") }
    }

    fun showDatePicker(){
        val datePickerDialog = DatePickerDialog(
            this,
            datepickerListner,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DATE),
        )
        datePickerDialog.show()
    }
    fun getGender() : String {
        lateinit var selectedGender : String
        val genderID = binding.rgGender.checkedRadioButtonId

        if (genderID != -1) {
            val selectedOption: RadioButton = findViewById(genderID)
            selectedGender = selectedOption.text.toString()
        }
        else
            selectedGender = "No Unspecified"

        return selectedGender
    }

    fun submit(view: View){


    }

    fun setupAutocompleteTV(){

        val language = arrayOf("C", "C++", "Java", ".NET", "iPhone", "Android", "ASP.NET", "PHP")
        val languageAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, language)
        binding.autoCompleteTVLanguages.setAdapter(languageAdapter)
    }

    fun setupSpinnerEntriesProgramatically(){
        val pronouns = ArrayAdapter.createFromResource(this , R.array.pronouns, android.R.layout.simple_spinner_item)
        pronouns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.pronoun.adapter = pronouns
    }

    fun setupSpinnerClickListner(){

        binding.pronoun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Toast.makeText(this@MainActivity, p0?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
                pronounText = p0?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {  } // will Never be called

        }
    }

}