package com.adilson.projetoFreelances.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.adapter.ListFreelasAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FragmentMain_ListShow : Fragment(R.layout.fragment_main_list_show) {


    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter = this.context?.let { ListFreelasAdapter(it, freela = emptyList()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<RecyclerView>(R.id.RCV_List_Freelas)
        layoutManager = LinearLayoutManager(activity)

        val recyclerView = view.findViewById<RecyclerView>(R.id.RCV_List_Freelas)
        recyclerView.adapter = adapter




        view.findViewById<FloatingActionButton>(R.id.FragMain_floatingActionButton).setOnClickListener{
            findNavController().navigate(R.id.FragMainToFragRegisterFreela)

        }

    }
}