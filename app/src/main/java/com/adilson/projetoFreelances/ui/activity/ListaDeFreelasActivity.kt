package com.adilson.projetoFreelances.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adilson.projetoFreelances.DAO.FreelasDAO
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.adapter.ListFreelasAdapter
import com.adilson.projetoFreelances.databinding.ActivityListaFreelasBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaDeFreelasActivity : AppCompatActivity() {

    //properties
    private lateinit var recycleView: RecyclerView
    private lateinit var BTN_goToAcitivityFormulario: FloatingActionButton


    // Para nao precisar ficar recriando o adapter
    private val dao = FreelasDAO()
    private val adapter = ListFreelasAdapter(context = this, freela = dao.lookAll())

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
        adapter.upDateAdapter(dao.lookAll())

        startActivityCadastroFreela()
    }

    private fun configRecycleView() {
        super.onResume()
       // recycleView = findViewById<RecyclerView>(R.id.activityMain_RCV_Freelas)
        recycleView = binding.activityMainRCVFreelas
        recycleView.adapter = adapter
    }


    private fun startActivityCadastroFreela() {

      //  BTN_goToAcitivityFormulario = findViewById<FloatingActionButton>(R.id.activityMain_floatingActionButton)
      BTN_goToAcitivityFormulario = binding.activityMainFloatingActionButton

        BTN_goToAcitivityFormulario.setOnClickListener() {
            startActivity(Intent(this, CadastroFreela::class.java))
        }
    }

}