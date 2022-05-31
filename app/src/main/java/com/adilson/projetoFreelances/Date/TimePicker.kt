package com.adilson.projetoFreelances.Date

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get((Calendar.MINUTE))

        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override  fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//
//        val textHora = String.format("%02d:%02d", hourOfDay, minute)
//        return textHora

    }

}