package com.adilson.projetoFreelances.ui.activity

import android.R
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DataBase.AppDatabase
import com.adilson.projetoFreelances.DataBase.dao.freelaDAO
import com.adilson.projetoFreelances.databinding.ActivityFormFreelaBinding
import com.adilson.projetoFreelances.model.Freelas
import com.adilson.projetoFreelances.ui.CHAVE_FREELA_INTENT
import java.text.SimpleDateFormat
import java.util.*


class CadastroFreela : AppCompatActivity() {


    private var textDate: String? = null
    private var textHora: String? = null

    private lateinit var btn_salvar: Button
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button

    private lateinit var freela: Freelas

    private var idFreela = 0L;


    private val binding by lazy {
        ActivityFormFreelaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        linksTheEditTextAndButton()
        configSaveButton()

        getDate()
        getTime()

        editarFreela()

    }

    private fun editarFreela() {
        intent.getParcelableExtra<Freelas>(CHAVE_FREELA_INTENT)?.let { freelaCarregado ->

            title = "Altera Evento"

            idFreela = freelaCarregado.id
            binding.editTextFotografo.setText(freelaCarregado.nomeFotografo)
            binding.editTextLocal.setText(freelaCarregado.local)
            binding.editTextNomeNoivos.setText(freelaCarregado.noivos)
            binding.editTextPhone.setText(freelaCarregado.celular)
            binding.datePickerButton.setText(freelaCarregado.date)
            binding.timePickerButton.setText(freelaCarregado.horas)

            setDadosNoObject(
                textName = freelaCarregado.nomeFotografo,
                textCelular = freelaCarregado.celular,
                textLocal = freelaCarregado.local,
                textNoivos = freelaCarregado.noivos
            )

            textDate = freelaCarregado.date
            textHora = freelaCarregado.horas

        }
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

        val formatDate = SimpleDateFormat("dd / MM / yyyy", Locale.getDefault())
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
        val db = AppDatabase.getInstance(this)
        val freelasDao = db.freelasDao()

        if (verificarCampos()){
            if(idFreela > 0){
                freelasDao.altera(freela)
            } else{
                freelasDao.salva(freela)
            }
            finish()

        } else
            Toast.makeText(this, "Campo nome e data sao obrigatorios", Toast.LENGTH_SHORT).show()

    }

    private fun verificarCampos(): Boolean {
        val status = !freela.nomeFotografo.isEmpty() && !freela.date.isNullOrEmpty()

        return status
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
            date = textDate,
            horas = textHora,
            nomeFotografo = textName,
            celular = textCelular,
            noivos = textNoivos,
            local = textLocal

        )
    }

}