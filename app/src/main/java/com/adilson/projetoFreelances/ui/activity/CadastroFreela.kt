package com.adilson.projetoFreelances.ui.activity

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DAO.FreelasDAO
import com.adilson.projetoFreelances.databinding.ActivityCadastroFreelaBinding
import com.adilson.projetoFreelances.model.Freelas
import java.text.SimpleDateFormat
import java.util.*


class CadastroFreela : AppCompatActivity() {

    private lateinit var textName: String
    private  var textDate: String? = null
    private lateinit var textNoivos: String
    private lateinit var textCelular: String
    private  var textHora: String? = null
    private lateinit var textLocal: String

    private lateinit var btn_salvar: Button
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button

    private var hour: Int = 0
    private var minute: Int = 0

    private var freela: Freelas = Freelas()

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

        val formatDate = SimpleDateFormat("dd / MM / yyyy", Locale.US)
        val getDate: Calendar = Calendar.getInstance()


        dateButton.setOnClickListener {

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.Theme_DeviceDefault_Dialog_MinWidth,
                { _, i, i2, i3 ->

                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, i)
                    selectDate.set(Calendar.MONTH, i2)
                    selectDate.set(Calendar.DAY_OF_MONTH, i3)
                    textDate = formatDate.format(selectDate.time)
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
            salvarDadosNoDAO()

        }
    }

    private fun salvarDadosNoDAO() {

        if(!( freela.nomeFotografo.isNullOrEmpty() || freela.date.isNullOrEmpty())){
            dao.add(freela)
            finish()
        } else{
            Toast.makeText(this, "Preencha O Nome e Data", Toast.LENGTH_SHORT).show()
        }

    }

    private fun linksTheEditTextAndButton() {

        textName = binding.editTextFotografo.text.toString()
        textNoivos = binding.editTextNomeNoivos.text.toString()
        textCelular = binding.editTextPhone.text.toString()
        textLocal = binding.editTextLocal.text.toString()

        timeButton = binding.timePickerButton
        btn_salvar = binding.buttonSalvarFreela
        dateButton = binding.datePickerButton

        setDadosNoObject()

    }

    private fun setDadosNoObject() {

        freela.nomeFotografo = textName.trim()
        freela.noivos = textNoivos.trim()
        freela.celular = textCelular.trim()
        freela.local = textLocal.trim()
        freela.date = textDate
        freela.horas = textHora
    }

}