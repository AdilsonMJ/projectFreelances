package com.adilson.projetoFreelances.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.databinding.ActivityObjetoFreelasBinding
import com.adilson.projetoFreelances.model.Freelas

class ListFreelasAdapter(
    private val context: Context,
    freela: List<Freelas> = emptyList(),
    var onClicked: (Freelas) -> Unit = {},
    var onClickEdit: (Freelas) -> Unit = {},
    var onClickRemove: (Freelas) -> Unit = {},
) :
    RecyclerView.Adapter<ListFreelasAdapter.FreesViewHolder>() {
    // Agora posso ter uma lista que pode ser mudada
    private val freelas = freela.toMutableList()

    //Onde sera criado a view
    inner class FreesViewHolder(binding: ActivityObjetoFreelasBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener  {

        //Tive que criar essa variavel mais nao faco a minima ideia do por que.
        private lateinit var fre: Freelas

        init {
            itemView.setOnClickListener{
                if (::fre.isInitialized){
                    onClicked(fre)
                }
            }

            itemView.setOnLongClickListener{
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(
                        R.menu.activity_list_menu, menu
                    )
                    setOnMenuItemClickListener(this@FreesViewHolder)
                }.show()
                true
            }

        }

        // val dataCasamento = itemView.findViewById<TextView>(R.id.rcv_text_dataFreela)
        val dataCasamento = binding.rcvTextDataFreela
        val telefoneFotografo = binding.rcvTextCelularFotografo
        val nameFotografo = binding.rcvTextNomeFotografo


        fun bind(freela: Freelas, onClicked: (Freelas) -> Unit) {

            this.fre = freela

            dataCasamento.text = fre.date.toString()
            nameFotografo.text = fre.nomeFotografo
            telefoneFotografo.text = fre.celular

        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when(it.itemId){
                    R.id.menu_edite ->{
                        onClickEdit(fre)
                    }

                    R.id.menu_remove ->{
                        onClickRemove(fre)
                    }
                }
            }
            return true
        }

    }

    //Onde vou inflar a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreesViewHolder {

        val binding = ActivityObjetoFreelasBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
//        val inflater = LayoutInflater.from(context)
//        val view = inflater.inflate(R.layout.activity_show_freelas, parent, false)
        return FreesViewHolder(binding = binding)

    }

    //Pegar a posicao dos itens
    override fun onBindViewHolder(holder: FreesViewHolder, position: Int) {
        val freela = freelas[position]
        holder.bind(freela, onClicked = onClicked)
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
