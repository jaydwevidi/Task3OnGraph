package com.example.task3ongraph

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
        setupSpinnerEntriesProgrammatically()
        setupDatePickerListner()
        setupTimePickerListner()

        binding.buttonDate.setOnClickListener {
            showDatePicker()
        }
        binding.buttonTime.setOnClickListener {
            showTimePicker()
        }


    }

    private fun setupTimePickerListner(){
        timePickerListner = object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
                //Toast.makeText(this@MainActivity, " time = $p1:$p2", Toast.LENGTH_SHORT).show()
                binding.buttonTime.text = "$hour:$minute"
            }
        }
    }

    private fun showTimePicker(){
        val timePicker = TimePickerDialog(this , timePickerListner ,  0 , 0 , false)
        timePicker.show()
    }

    private fun setupDatePickerListner(){
        datepickerListner = object  : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
                //Toast.makeText(this@MainActivity, "$year ${month+1} $date", Toast.LENGTH_SHORT).show()
                binding.buttonDate.text = "$date-${month+1}-$year"
            }
        }
        //datepickerListner = DatePickerDialog.OnDateSetListener { p0, p1, p2, p3 -> TODO("Not yet implemented") }
    }

    private fun showDatePicker(){
        val datePickerDialog = DatePickerDialog(
            this,
            datepickerListner,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DATE),
        )
        datePickerDialog.show()
    }

    private fun getGender() : String {
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

        val languageSelected : String = binding.autoCompleteTVLanguages.text.toString()
        val pronoun = pronounText
        val name = binding.etNameFull.text

        val fullString = "$pronoun $name \n" +
                "language = $languageSelected ," +
                " date = ${binding.buttonDate.text}"

        if(!binding.checkboxAge.isChecked) {
            Toast.makeText(view.context, "Only adults allowed", Toast.LENGTH_SHORT).show()
            return
        }

        binding.progressBar.max = 1000
        ObjectAnimator.ofInt(binding.progressBar , "progress" , 990 )
            .setDuration(2000)
            .start()

        val timer = object: CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                val intent = Intent(applicationContext , HomeActivity::class.java)
                intent.putExtra("user" , fullString)
                startActivity(intent)
            }
        }
        timer.start()

    }

    private fun setupAutocompleteTV(){

        val language = arrayOf("C", "Kotlin" , "C++", "Java", ".NET", "iPhone", "Android", "ASP.NET", "PHP")
        val languageAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, language)
        binding.autoCompleteTVLanguages.setAdapter(languageAdapter)
    }

    private fun setupSpinnerEntriesProgrammatically(){
        val pronouns = ArrayAdapter.createFromResource(this , R.array.pronouns, android.R.layout.simple_spinner_item)
        pronouns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.pronoun.adapter = pronouns
    }

    private fun setupSpinnerClickListner(){

        binding.pronoun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                //Toast.makeText(this@MainActivity, p0?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
                pronounText = p0?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {  } // will Never be called

        }
    }

}