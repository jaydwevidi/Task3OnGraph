package com.example.task3ongraph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task3ongraph.databinding.ActivityHomeBinding
import com.smarteist.autoimageslider.SliderView

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRV()
        setupSlider()
    }
    fun getRandomList() : List<String> {
        val list = mutableListOf<String>()
        list.add("dfa dfasdfa")
        list.add("dfa dfasdfdsfa")
        list.add("dfa dfasdfa")
        list.add("dfa dfasdfa")
        list.add("dfa dfasdfa")
        return list
    }
    fun setupRV() {
        binding.homeRV.apply {
            adapter = MyRvAdapter(getRandomList())
            layoutManager = LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false)
        }
    }
    fun setupSlider(){

        val list = mutableListOf<String>()

        list.add("https://wallpapercave.com/wp/wp3850825.jpg")
        list.add("https://wallpapercave.com/wp/wp6058934.jpg")
        list.add("https://wallpapercave.com/wp/wp4676567.jpg")
        list.add("https://people.sc.fsu.edu/~jburkardt/data/jpg/auburn_logo.jpg")

        binding.sliderView.setSliderAdapter(
            SlideAdapter(list , context =  applicationContext )
        )
    }
}