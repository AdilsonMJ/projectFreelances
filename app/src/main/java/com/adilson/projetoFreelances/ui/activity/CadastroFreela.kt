package com.adilson.projetoFreelances.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DAO.FreelasDAO
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.model.Freelas


class CadastroFreela : AppCompatActivity(R.layout.activity_cadastro_freela) {

    private lateinit var textName: String
    private lateinit var textDate: String
    private lateinit var textNoivos: String
    private lateinit var textCelular: String
    private lateinit var textHora: String
    private lateinit var textLocal: String
    private lateinit var btn_salvar: Button

    private val dao = FreelasDAO()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linksTheEditTextAndButton()
        configSaveButton()

    }


    private fun configSaveButton() {

        btn_salvar.setOnClickListener {
            linksTheEditTextAndButton()

            if (textDate.isBlank() && textName.isBlank()){
                Toast.makeText(this, "Nome e Data Sao OBRIGATORIOS", Toast.LENGTH_SHORT).show()
            } else{
                salvarDadosNoDAO()
                finish()
            }
        }
    }

    private fun salvarDadosNoDAO() {

        val freela = Freelas(
            date = textDate,
            horas =  textHora,
            nomeFotografo = textName,
            celular = textCelular,
            noivos =  textNoivos,
            local = textLocal
        )
        dao.add(freela)
    }

    private fun linksTheEditTextAndButton() {

        textName = findViewById<EditText>(R.id.editTextFotografo).text.toString()

        textDate = findViewById<EditText>(R.id.editTextDate).text.toString()

        textNoivos = findViewById<EditText>(R.id.editTextNomeNoivos).text.toString()

        textCelular = findViewById<EditText>(R.id.editTextPhone).text.toString()

        textHora = findViewById<EditText>(R.id.editTextTime).text.toString()

        textLocal = findViewById<EditText>(R.id.editTextLocal).text.toString()

        btn_salvar = findViewById<Button>(R.id.buttonSalvarFreela)

    }
}