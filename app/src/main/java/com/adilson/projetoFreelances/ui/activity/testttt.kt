//package com.adilson.projetoFreelances.ui.activity
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.app.TimePickerDialog.OnTimeSetListener
//import android.widget.TimePicker
//import android.app.TimePickerDialog
//import android.view.View
//import android.widget.Button
//import java.util.*
//
//class MainActivity : AppCompatActivity() {
//    var timeButton: Button? = null
//    var hour = 0
//    var minute = 0
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        timeButton = findViewById(R.id.timeButton)
//    }
//
//    fun popTimePicker(view: View?) {
//        val onTimeSetListener = OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
//            hour = selectedHour
//            minute = selectedMinute
//            timeButton!!.text = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
//        }
//
//        // int style = AlertDialog.THEME_HOLO_DARK;
//        val timePickerDialog =
//            TimePickerDialog(this,  /*style,*/onTimeSetListener, hour, minute, true)
//        timePickerDialog.setTitle("Select Time")
//        timePickerDialog.show()
//    }
//}