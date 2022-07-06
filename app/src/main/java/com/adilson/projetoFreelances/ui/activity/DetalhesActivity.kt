package com.adilson.projetoFreelances.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DataBase.AppDatabase
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.databinding.ActivityDetalhesBinding
import com.adilson.projetoFreelances.model.Freelas
import com.adilson.projetoFreelances.ui.CHAVE_FREELA_ID
import java.text.SimpleDateFormat


class DetalhesActivity : AppCompatActivity() {


    private var idFreela: Long = 0L
    private var freela: Freelas? = null
    private var title: String = "Detalhes"
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")


    private val freeDAO by lazy {
        AppDatabase.getInstance(this).freelasDao()
    }

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setTitle(title)

        getDatesFromIntent()

        btn_voltar()
    }

    override fun onResume() {
        super.onResume()
        buscaProdutoNoDB()
    }

    private fun buscaProdutoNoDB() {
        freela = freeDAO.buscarPorId(idFreela)

        freela?.let {
            filltheFilds(it)
        } ?: finish()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_list_menu_edite_cancel, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_edite -> {
                Intent(this, CadastroFreela::class.java).apply {
                    putExtra(CHAVE_FREELA_ID, idFreela)
                    startActivity(this)
                }


            }
            R.id.menu_remove -> {
                freela?.let {
                    freeDAO.remove(it)
                    finish()
                }


            }
            else -> super.onOptionsItemSelected(item)
        }

        super.onOptionsItemSelected(item)
        return true
    }


    private fun btn_voltar() {
        val btn: Button = binding.buttonVoltar
        btn.setOnClickListener {
            finish()
        }
    }

    private fun getDatesFromIntent() {
        idFreela = intent.getLongExtra(CHAVE_FREELA_ID, 0L)
    }

    private fun filltheFilds(load: Freelas) {

        with(binding) {
            showDate.text = dateFormat.format(load.date)
            showHora.text = load.horas
            showLocal.text = load.local
            showNameNoivos.text = load.noivos
            showNameProfissional.text = load.nomeFotografo
        }
    }
}