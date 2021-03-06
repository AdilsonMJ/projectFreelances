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
        adapter.upDateAdapter(freelaDAO.buscaTodos(Date().time))

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


}
