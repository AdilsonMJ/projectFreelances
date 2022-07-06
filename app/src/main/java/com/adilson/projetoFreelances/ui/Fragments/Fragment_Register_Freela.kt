package com.adilson.projetoFreelances.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.adilson.projetoFreelances.R
import com.adilson.projetoFreelances.databinding.FragmentRegisterFreelaBinding


class Fragment_Register_Freela : Fragment(R.layout.fragment_register_freela) {

    private var _binding: FragmentRegisterFreelaBinding? = null
    private val binding:  FragmentRegisterFreelaBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterFreelaBinding
            .inflate(
                inflater,
                container,
                false
            )

        return binding.root
    }
}