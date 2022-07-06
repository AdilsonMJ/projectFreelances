package com.adilson.projetoFreelances.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adilson.projetoFreelances.R


class MainActivity : AppCompatActivity() {

//    //properties
//    private lateinit var BTN_goToAcitivityFormulario: FloatingActionButton
//
//    // Para nao precisar ficar recriando o adapter
//    private val adapter =
//        ListFreelasAdapter(context = this@MainActivity, freela = emptyList())
//
//    // Set Binding View
//    private val binding by lazy {
//        ActivityListaFreelasBinding.inflate(layoutInflater)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Freelances"

//        configRecycleView()
//        startActivityCadastroFreela()


    }

    /*
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
*/

}
