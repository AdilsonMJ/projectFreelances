package com.adilson.projetoFreelances.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.databinding.ActivityShowFreelasBinding
import com.adilson.projetoFreelances.model.Freelas

class ListFreelasAdapter(freela: List<Freelas>, private val context: Context) :
    RecyclerView.Adapter<ListFreelasAdapter.ViewHolder>() {

    // Agora posso ter uma lista que pode ser mudada
    private val freelas = freela.toMutableList()


    //Onde sera criado a view
    class ViewHolder(binding: ActivityShowFreelasBinding) : RecyclerView.ViewHolder(binding.root) {

        // val dataCasamento = itemView.findViewById<TextView>(R.id.rcv_text_dataFreela)
        val dataCasamento = binding.rcvTextDataFreela
        val horaDoCasamento = binding.rcvTextHorasFreela
        val telefoneFotografo = binding.rcvTextCelularFotografo
        val nameFotografo = binding.rcvTextNomeFotografo
        val nomeNoivos = binding.rcvTextNomeNoivos
        val localCasamento = binding.rcvTextLocalDaCerimonia


        fun vincula(freela: Freelas) {

            dataCasamento.text = freela.date
            horaDoCasamento.text = freela.horas
            nameFotografo.text = freela.nomeFotografo
            telefoneFotografo.text = freela.celular
            nomeNoivos.text = freela.noivos
            localCasamento.text = freela.local

        }
    }

    //Onde vou inflar a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ActivityShowFreelasBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.activity_show_freelas, parent, false)
        return ViewHolder(binding = binding)

    }

    //Pegar a posicao dos itens
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val freela = freelas[position]
        holder.vincula(freela)
    }

    // Quantos itens vai ter a view
    override fun getItemCount(): Int = freelas.size

    //Atualizar Adapter
    fun upDateAdapter(freelas: List<Freelas>) {
        this.freelas.clear()
        this.freelas.addAll(freelas)
        notifyDataSetChanged()
    }

}
