package com.gautamtuteja.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gautamtuteja.a7minuteworkout.databinding.ActivityExcersiceBinding
import com.gautamtuteja.a7minuteworkout.databinding.ActivityMainBinding
import com.gautamtuteja.a7minuteworkout.databinding.CustomBackConfirmBinding
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList

class ExcersiceActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExcersiceBinding? = null

    private var restTimer : CountDownTimer? =null
    private var restProgress =0

    private var resttimerduration: Long =10
    private var excetimerduration: Long =30

    private var excerciseTimer:CountDownTimer?=null
    private var excersiceProgress=0


    private var excersicelist : ArrayList<ExcerModel>? = null
    private var currexcpos =-1


    private var tts: TextToSpeech? = null


    private var player: MediaPlayer? = null


    private var excersiceAdapter : ExcersiceStatusAdapter?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcersiceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarexc)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarexc?.setNavigationOnClickListener{
            customDialogBackBtn()
        }

        excersicelist = Constants.defaultExcesice()

        tts = TextToSpeech(this,this)


        setupRestView()
        setupExcersiceStatusRecyclerView()


    }


    private fun customDialogBackBtn(){
        val cusDialog =Dialog(this)
        val dialogbinding = CustomBackConfirmBinding.inflate(layoutInflater)

        cusDialog.setContentView(dialogbinding.root)

        cusDialog.setCanceledOnTouchOutside(false)

        dialogbinding.yesbtn.setOnClickListener{
            this.finish()
            cusDialog.dismiss()
        }
        dialogbinding.nobtn.setOnClickListener {
            cusDialog.dismiss()
        }

        cusDialog.show()

    }



    private fun setupExcersiceStatusRecyclerView(){
        binding?.excerstatus?.layoutManager=LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL,false
        )
        excersiceAdapter = ExcersiceStatusAdapter(excersicelist!!)
        binding?.excerstatus?.adapter = excersiceAdapter
    }



    private fun setupRestView(){

        try {
            val soundURI = Uri.parse(
                "android.resource://com.gautamtuteja.a7minuteworkout/"
                        + R.raw.notification)

            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping= false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }


        binding?.flrestview?.visibility=View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE

        binding?.excername?.visibility=View.INVISIBLE
        binding?.flexcersiceprogress?.visibility=View.INVISIBLE
        binding?.excimage?.visibility=View.INVISIBLE

        binding?.nextexclabel?.visibility=View.VISIBLE
        binding?.nextexc?.visibility=View.VISIBLE



        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }



        binding?.nextexc?.text= excersicelist!![currexcpos+1].getName()

        setrestProgressBar()
    }



    private fun setrestProgressBar(){
        binding?.progressbar?.progress = restProgress

        restTimer = object : CountDownTimer(resttimerduration*1000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressbar?.progress = 10- restProgress
                binding?.Timer?.text=(10-restProgress).toString()
            }

            override fun onFinish() {
                currexcpos++

                excersicelist!![currexcpos].setSelected(true)
                excersiceAdapter!!.notifyDataSetChanged()
                setupExcersiceView()
            }
        }.start()
    }



    private fun setupExcersiceView(){

        binding?.flrestview?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE

        binding?.excername?.visibility=View.VISIBLE
        binding?.flexcersiceprogress?.visibility=View.VISIBLE
        binding?.excimage?.visibility=View.VISIBLE

        binding?.nextexclabel?.visibility=View.INVISIBLE
        binding?.nextexc?.visibility=View.INVISIBLE


        if(excerciseTimer!=null){
            excerciseTimer?.cancel()
            excersiceProgress=0
        }


        SpeakOut(excersicelist!![currexcpos].getName())

        binding?.excimage?.setImageResource(excersicelist!![currexcpos].getImage())
        binding?.excername?.text = excersicelist!![currexcpos].getName()


        setExcersiceProgressBar()
    }



    private fun setExcersiceProgressBar(){
        binding?.excersiceprogressbar?.progress = excersiceProgress

        excerciseTimer = object : CountDownTimer(excetimerduration*1000,1000){
            override fun onTick(p0: Long) {
                excersiceProgress++
                binding?.excersiceprogressbar?.progress = 30- excersiceProgress
                binding?.excersiceTimer?.text=(30-excersiceProgress).toString()
            }

            override fun onFinish() {

                if(currexcpos<excersicelist?.size!!-1){
                    excersicelist!![currexcpos].setSelected(false)
                    excersicelist!![currexcpos].setComplete(true)
                    excersiceAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else{
                    val intent = Intent(this@ExcersiceActivity, FinishActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }.start()
    }



    override fun onDestroy() {
        super.onDestroy()

        if(restTimer!=null){
            restTimer?.cancel()
            restProgress=0
        }

        if(excerciseTimer!=null){
            excerciseTimer?.cancel()
            excersiceProgress=0
        }


        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player != null){
            player!!.stop()
        }


        binding = null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported")
            }
        } else{
            Log.e("TTS","INTIALIZATION FAILED")
        }
    }

    private fun SpeakOut(text : String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}