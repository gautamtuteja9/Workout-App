package com.gautamtuteja.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.gautamtuteja.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS = "METRIC_UNIT"
        private const val US_UNITS = "US_UNIT"
    }

    private var currentVisibleView: String = METRIC_UNITS

    private var binding: ActivityBmiBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        setSupportActionBar(binding?.toolbarBmi)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title="CALCULATE BMI"
        }
        binding?.toolbarBmi?.setNavigationOnClickListener{
            onBackPressed()
        }

         makevisibleMetricUnit()

        binding?.rgunits?.setOnCheckedChangeListener{_,checked:Int ->
            if (checked == R.id.rbmetricunit){
                makevisibleMetricUnit()
            }else{
                makevisibleUSUnit()
            }
        }

        binding?.btncalcbmi?.setOnClickListener{
            calculateunits()
        }
    }


    private fun makevisibleMetricUnit(){
        currentVisibleView = METRIC_UNITS
        binding?.tiheight?.visibility =View.VISIBLE
        binding?.tiweight?.visibility =View.VISIBLE
        binding?.tiusweight?.visibility=View.INVISIBLE
        binding?.tiusheightft?.visibility=View.INVISIBLE
        binding?.tiusheightin?.visibility=View.INVISIBLE

        binding?.etheight?.text!!.clear()
        binding?.etweight?.text!!.clear()

        binding?.lldisplaybmi?.visibility =View.INVISIBLE
    }

    private fun makevisibleUSUnit(){
        currentVisibleView = US_UNITS
        binding?.tiheight?.visibility =View.INVISIBLE
        binding?.tiweight?.visibility =View.INVISIBLE
        binding?.tiusweight?.visibility=View.VISIBLE
        binding?.tiusheightft?.visibility=View.VISIBLE
        binding?.tiusheightin?.visibility=View.VISIBLE

        binding?.etusheightft?.text!!.clear()
        binding?.etusheightin?.text!!.clear()
        binding?.etusweight?.text!!.clear()

        binding?.lldisplaybmi?.visibility =View.INVISIBLE
    }

    private fun displayBmiResult(bmi : Float){

        val bmilabel : String
        val bmidesc : String

        if(bmi.compareTo(15f)<=0){
            bmilabel = "VERY SEVERELY UNDERWEIGHT"
            bmidesc = "TAKE CARE!! EAT MUCH"
        }else if (bmi.compareTo(15f)>0 && bmi.compareTo(16f)<=0){
            bmilabel = "SEVERELY UNDERWEIGHT"
            bmidesc = "TAKE CARE!! EAT MUCH"
        }else if (bmi.compareTo(16f)>0 && bmi.compareTo(18.5f)<=0){
            bmilabel = "UNDERWEIGHT"
            bmidesc = "TAKE CARE!! EAT MUCH"
        }else if (bmi.compareTo(18.5f)>0 && bmi.compareTo(25f)<=0){
            bmilabel = "NORMAL"
            bmidesc = "CONGO!! YOU ARE FIT"
        }else if (bmi.compareTo(25f)>0 && bmi.compareTo(30f)<=0){
            bmilabel = "OVERWEIGHT"
            bmidesc = "TAKE CARE!! WORKOUT MUCH"
        }else if (bmi.compareTo(30f)>0 && bmi.compareTo(35f)<=0){
            bmilabel = "MODERATELY OBESE"
            bmidesc = "TAKE CARE!! WORKOUT MUCH"
        }else if (bmi.compareTo(35f)>0 && bmi.compareTo(40f)<=0){
            bmilabel = "SEVERELY OBESE"
            bmidesc = "TAKE CARE!! WORKOUT MUCH"
        }else{
            bmilabel = "VERY SEVERELY OBESE"
            bmidesc = "TAKE CARE!! WORKOUT MUCH"
        }

        val bmivalue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.lldisplaybmi?.visibility= View.VISIBLE
        binding?.bmivalue?.text = bmivalue
        binding?.tvbmiType?.text = bmilabel
        binding?.bmidesc?.text =bmidesc

    }


    private fun validateMetricUnit():Boolean{
        var isValid =true
        if(binding?.etweight?.text.toString().isEmpty()){
            isValid=false
        }else if(binding?.etheight?.text.toString().isEmpty()){
            isValid=false
        }
        return isValid
    }



    private fun calculateunits(){
        if(currentVisibleView == METRIC_UNITS){
            if(validateMetricUnit()){
                val weight :Float = binding?.etweight?.text.toString().toFloat()
                val height :Float = binding?.etheight?.text.toString().toFloat() /100

                val bmi = weight / (height * height)
                displayBmiResult(bmi)
            }else{
                Toast.makeText(this, "Please Enter Valid Values",
                    Toast.LENGTH_SHORT).show()
            }
        }else{
            if(validateUsUnit()){
                val heightft :String = binding?.etusheightft?.text.toString()
                val heightin :String = binding?.etusheightin?.text.toString()
                val weightus : Float = binding?.etusweight?.text.toString().toFloat()

                val heightus = heightin.toFloat() + heightft.toFloat() *12

                val bmi = 703*(weightus/(heightus*heightus))
                displayBmiResult(bmi)
            }else{
                Toast.makeText(this, "Please Enter Valid Values",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun validateUsUnit():Boolean{
        var isValid =true
        when {
            binding?.etusweight?.text.toString().isEmpty() -> {
                isValid=false
            }
            binding?.etusheightft?.text.toString().isEmpty() -> {
                isValid=false
            }
            binding?.etusheightin?.text.toString().isEmpty() -> {
                isValid=false
            }
        }
        return isValid
    }
}