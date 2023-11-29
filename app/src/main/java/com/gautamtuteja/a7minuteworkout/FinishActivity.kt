package com.gautamtuteja.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.gautamtuteja.a7minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar as Calendar

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.finishbtn?.setOnClickListener{
            finish()
        }


        val Dao = (application as WorkoutApp).db.historyDao()
        addDateToDB(Dao)


    }

    private fun addDateToDB(historyDao: HistoryDao) {

        val c = Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date:",""+dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())

        val date = sdf.format(dateTime)
        Log.e("Formatted Date:",""+date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.e("Date:","Added.....")
        }

    }
}