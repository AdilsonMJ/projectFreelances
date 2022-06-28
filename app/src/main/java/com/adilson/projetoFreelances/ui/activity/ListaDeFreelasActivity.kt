package com.adilson.projetoFreelances.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.DataBase.AppDatabase
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.adapter.ListFreelasAdapter
import com.adilson.projetoFreelances.databinding.ActivityListaFreelasBinding
import com.adilson.projetoFreelances.ui.CHAVE_FREELA_ID
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*


class ListaDeFreelasActivity : AppCompatActivity() {

    //properties
    private lateinit var BTN_goToAcitivityFormulario: FloatingActionButton

    private var MOSTRA_ANTIGOS: Boolean = false

    // Para nao precisar ficar recriando o adapter
    private val adapter =
        ListFreelasAdapter(context = this@ListaDeFreelasActivity, freela = emptyList())

    // Set Binding View
    private val binding by lazy {
        ActivityListaFreelasBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Freelances"

        configRecycleView()
        startActivityCadastroFreela()



    }

    override fun onResume() {
        super.onResume()

        val db = AppDatabase.getInstance(this)
        val freelaDAO = db.freelasDao()

        if (!MOSTRA_ANTIGOS){
            adapter.upDateAdapter(freelaDAO.buscaTodosMaiorDataAtual(Date().time))
        } else{
            adapter.upDateAdapter((freelaDAO.buscaTodosAntigo(Date().time)))
        }

    }

    private fun configRecycleView() {

        // recycleView = findViewById<RecyclerView>(R.id.activityMain_RCV_Freelas)
        val recyclerView = binding.activityMainRCVFreelas
        recyclerView.adapter = adapter

        adapter.onClicked = {
            startActivity(Intent(
                this, DetalhesActivity::class.java
            ).apply {
                putExtra(CHAVE_FREELA_ID, it.id)
            })
        }

        adapter.onClickRemove = {
            val db = AppDatabase.getInstance(this)
            val freelasDao = db.freelasDao()
            freelasDao.remove(it)
            onResume()
        }

        adapter.onClickEdit = {
            startActivity(Intent(this, CadastroFreela::class.java).apply {
                putExtra(CHAVE_FREELA_ID, it.id)
            })

        }

    }

    private fun startActivityCadastroFreela() {

        //  BTN_goToAcitivityFormulario = findViewById<FloatingActionButton>(R.id.activityMain_floatingActionButton)
        BTN_goToAcitivityFormulario = binding.activityMainFloatingActionButton
        BTN_goToAcitivityFormulario.setOnClickListener() {
            startActivity(Intent(this, CadastroFreela::class.java))
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_list_menu_show_old_freelas_and_new_freelas, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_freelas -> {
                MOSTRA_ANTIGOS = false
                onResume()
            }
            R.id.menu_oldFreelas -> {

                MOSTRA_ANTIGOS = true
                onResume()
            }
            else -> super.onOptionsItemSelected(item)
        }

        super.onOptionsItemSelected(item)
        return true
    }

}