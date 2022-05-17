package com.adilson.projetoFreelances.ui.activity

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DAO.FreelasDAO
import com.adilson.projetoFreelances.databinding.ActivityCadastroFreelaBinding
import com.adilson.projetoFreelances.model.Freelas
import java.text.SimpleDateFormat
import java.util.*


class CadastroFreela : AppCompatActivity() {

    private lateinit var textName: String
    private lateinit var textDate: String
    private lateinit var textNoivos: String
    private lateinit var textCelular: String
    private lateinit var textHora: String
    private lateinit var textLocal: String

    private lateinit var btn_salvar: Button
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button

    private  var hour: Int = 0
    private  var minute: Int = 0


    private val dao = FreelasDAO()


    private val binding by lazy {
        ActivityCadastroFreelaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        linksTheEditTextAndButton()
        configSaveButton()

        getDate()
        getTime()



    }

    private fun getTime() {
        timeButton.setOnClickListener {

            val onTimeSetListener =
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    hour = selectedHour
                    minute = selectedMinute
                    textHora =
                        String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
                    timeButton.text = textHora
                }

            // int style = AlertDialog.THEME_HOLO_DARK;
            val timePickerDialog =
                TimePickerDialog(this,  /*style,*/onTimeSetListener, hour, minute, true)
            timePickerDialog.show()

        }
    }

    private fun getDate() {

        var formatDate = SimpleDateFormat("dd / MM / yyyy", Locale.US)
        var getDate: Calendar = Calendar.getInstance()


        dateButton.setOnClickListener {

            var datePickerDialog = DatePickerDialog(
                this,
                R.style.Theme_DeviceDefault_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { _, i, i2, i3 ->

                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, i)
                    selectDate.set(Calendar.MONTH, i2)
                    selectDate.set(Calendar.DAY_OF_MONTH, i3)
                    textDate = formatDate.format(selectDate.time)
                    Toast.makeText(this, "Date : $textDate", Toast.LENGTH_SHORT).show()

                    dateButton.text = textDate

                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }

    private fun configSaveButton() {

        btn_salvar.setOnClickListener {
            linksTheEditTextAndButton()

            if (textDate.isBlank() && textName.isBlank()) {
                Toast.makeText(this, "Nome e Data Sao OBRIGATORIOS", Toast.LENGTH_SHORT).show()
            } else {
                salvarDadosNoDAO()
                finish()
            }
        }
    }

    private fun salvarDadosNoDAO() {

        val freela = Freelas(
            date = textDate,
            horas = textHora,
            nomeFotografo = textName,
            celular = textCelular,
            noivos = textNoivos,
            local = textLocal
        )
        dao.add(freela)
    }

    private fun linksTheEditTextAndButton() {

        textName = binding.editTextFotografo.text.toString()
        textNoivos = binding.editTextNomeNoivos.text.toString()
        textCelular = binding.editTextPhone.text.toString()
        textLocal = binding.editTextLocal.text.toString()

        timeButton = binding.timePickerButton
        btn_salvar = binding.buttonSalvarFreela
        dateButton = binding.datePickerButton

    }


}