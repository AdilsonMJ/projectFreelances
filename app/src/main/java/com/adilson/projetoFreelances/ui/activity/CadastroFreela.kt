package com.adilson.projetoFreelances.ui.activity

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DataBase.AppDatabase
import com.adilson.projetoFreelances.databinding.ActivityFormFreelaBinding
import com.adilson.projetoFreelances.model.Freelas
import com.adilson.projetoFreelances.ui.CHAVE_FREELA_ID
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class CadastroFreela : AppCompatActivity() {


    private var date: Date? = null
    private var textHora: String? = null

    private lateinit var btn_salvar: Button
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button

    private lateinit var freela: Freelas

    private var idFreela = 0L;


    private val binding by lazy {
        ActivityFormFreelaBinding.inflate(layoutInflater)
    }

    private val freelasDAO by lazy {
        val db = AppDatabase.getInstance(this)
        db.freelasDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        linksTheEditTextAndButton()
        configSaveButton()

        getDate()
        getTime()

        editarFreelaGetID()

    }

    override fun onResume() {
        super.onResume()
        freelasDAO.buscarPorId(idFreela)?.let {
            preencherCamposComDadosDoBD(it)
        }
    }


    private fun preencherCamposComDadosDoBD(it: Freelas) {
        title = "Altera Freela"
        binding.editTextLocal.setText(it.local)
        binding.editTextNomeNoivos.setText(it.noivos)
        binding.editTextFotografo.setText(it.nomeFotografo)
        binding.editTextPhone.setText(it.celular)
        binding.datePickerButton.text = it.date.toString()
        binding.timePickerButton.text = it.horas

        setDadosNoObject(
            textName = it.nomeFotografo,
            textLocal = it.local,
            textNoivos = it.noivos,
            textCelular = it.celular
        )
        date = it.date
        textHora = it.horas

    }

    private fun editarFreelaGetID() {
        idFreela = intent.getLongExtra(CHAVE_FREELA_ID, 0L)
    }

    private fun getTime() {
        timeButton.setOnClickListener {

            //     TimePickerFragment().show(supportFragmentManager, "timePicker")
            var hour: Int = 0
            var minute: Int = 0

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

        dateButton.setOnClickListener {

            val calendarContraintBuilder = CalendarConstraints.Builder()
            calendarContraintBuilder.setValidator(DateValidatorPointForward.now())

            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Selec date")
                .setCalendarConstraints(calendarContraintBuilder.build())
                .build()

            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {

                val dateFormat = SimpleDateFormat("dd/MM/yyyy")

                // ALERT! CONVERT STRING > LONG > DATE IN DB CONVERT AGAIN TO LONG
                val dat = dateFormat.format(Date(it))
                val d  = Date.parse(dat)
                date = Date(d)

                dateButton.text = dat

            }

        }

    }

    private fun configSaveButton() {

        btn_salvar.setOnClickListener {
            linksTheEditTextAndButton()
            salvarDadosNoDAO()

        }
    }

    private fun salvarDadosNoDAO() {


        if (verificarCampos()) {
//
            freelasDAO.salva(freela)
            finish()

        } else
            Toast.makeText(this, "Campo nome e data sao obrigatorios", Toast.LENGTH_SHORT).show()

    }

    private fun verificarCampos(): Boolean {
        return !freela.nomeFotografo.isEmpty() && !freela.date?.toString()?.isEmpty()!!

    }

    private fun linksTheEditTextAndButton() {

        val textName = binding.editTextFotografo.text.toString().trim()
        val textNoivos = binding.editTextNomeNoivos.text.toString().trim()
        val textCelular = binding.editTextPhone.text.toString().trim()
        val textLocal = binding.editTextLocal.text.toString().trim()
        timeButton = binding.timePickerButton
        btn_salvar = binding.buttonSalvarFreela
        dateButton = binding.datePickerButton

        setDadosNoObject(textName, textNoivos, textCelular, textLocal)

    }

    private fun setDadosNoObject(
        textName: String,
        textNoivos: String,
        textCelular: String,
        textLocal: String
    ) {


        freela = Freelas(
            id = idFreela,
            date = date,
            horas = textHora,
            nomeFotografo = textName,
            celular = textCelular,
            noivos = textNoivos,
            local = textLocal

        )
    }

}