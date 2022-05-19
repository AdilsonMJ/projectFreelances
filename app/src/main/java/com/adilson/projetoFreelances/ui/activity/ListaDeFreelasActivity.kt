package com.adilson.projetoFreelances.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.adilson.projetoFreelances.DAO.FreelasDAO
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.adapter.ListFreelasAdapter
import com.adilson.projetoFreelances.databinding.ActivityListaFreelasBinding
import com.adilson.projetoFreelances.model.Freelas
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListaDeFreelasActivity : AppCompatActivity() {

    //properties
    private lateinit var recyclerView: RecyclerView
    private lateinit var BTN_goToAcitivityFormulario: FloatingActionButton

    private var freela: Freelas = Freelas()

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
        recyclerView = binding.activityMainRCVFreelas
        recyclerView.adapter = adapter
    }


    private fun startActivityCadastroFreela() {

        //  BTN_goToAcitivityFormulario = findViewById<FloatingActionButton>(R.id.activityMain_floatingActionButton)
        BTN_goToAcitivityFormulario = binding.activityMainFloatingActionButton

        BTN_goToAcitivityFormulario.setOnClickListener() {
            startActivity(Intent(this, CadastroFreela::class.java))
        }
    }

    private fun removeFreela(freela: Freelas) {
        dao.remove(freela)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.activity_list_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_remove -> {
                Toast.makeText(this, "Remove", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu_edite -> {
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }


    }


}