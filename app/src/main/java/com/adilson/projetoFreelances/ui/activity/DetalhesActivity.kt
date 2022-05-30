package com.adilson.projetoFreelances.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.databinding.ActivityDetalhesBinding
import com.adilson.projetoFreelances.model.Freelas
import com.adilson.projetoFreelances.ui.CHAVE_FREELA_INTENT

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        uploadFreelas()
        btn_voltar()
    }

    private fun btn_voltar() {
        val btn: Button = binding.buttonVoltar
        btn.setOnClickListener{
            finish()
        }
    }

    private fun uploadFreelas() {
        intent.getParcelableExtra<Freelas>(CHAVE_FREELA_INTENT)?.let { freelas ->
            filltheFilds(freelas)
        } ?: finish()
    }

    private fun filltheFilds(load: Freelas) {
        with(binding){
            showDate.text = load.date
            showHora.text = load.horas
            showLocal.text = load.local
            showNameNoivos.text = load.noivos
            showNameProfissional.text = load.nomeFotografo
        }
    }
}