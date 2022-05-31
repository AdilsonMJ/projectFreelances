package com.adilson.projetoFreelances.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.adilson.projetoFreelances.DataBase.AppDatabase
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.databinding.ActivityDetalhesBinding
import com.adilson.projetoFreelances.model.Freelas
import com.adilson.projetoFreelances.ui.CHAVE_FREELA_INTENT

class DetalhesActivity : AppCompatActivity() {

    private lateinit var freela: Freelas
    private lateinit var title: String;

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        uploadFreelas()
        btn_voltar()
        setTitle(title)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::freela.isInitialized){
            val db = AppDatabase.getInstance(this)
            val freelasDao = db.freelasDao()
            return when (item.itemId){
                R.id.menu_edite -> {
                    true

                }
                R.id.menu_remove -> {
                    freelasDao.remove(freela)
                    finish()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        super.onOptionsItemSelected(item)
        return true
    }


    private fun btn_voltar() {
        val btn: Button = binding.buttonVoltar
        btn.setOnClickListener{
            finish()
        }
    }

    private fun uploadFreelas() {
        intent.getParcelableExtra<Freelas>(CHAVE_FREELA_INTENT)?.let { freelas ->
            freela = freelas
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
            title = "${load.date} - ${load.nomeFotografo}"
        }
    }
}