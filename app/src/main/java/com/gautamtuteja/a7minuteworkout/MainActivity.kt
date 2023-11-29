package com.gautamtuteja.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.gautamtuteja.a7minuteworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var binding : ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        
        
        //val flstart : FrameLayout = findViewById(R.id.flstart)


        binding?.flstart?.setOnClickListener(){
            val intent = Intent(this,ExcersiceActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener(){
            val intent = Intent(this,BMIActivity::class.java)
            startActivity(intent)
        }

        binding?.flHISTORY?.setOnClickListener {
            val intent = Intent(this,History::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}