package com.adilson.projetoFreelances.DAO

import com.adilson.projetoFreelances.model.Freelas

class FreelasDAO {

    companion object {

        private val freelasList = mutableListOf<Freelas>()

    }

    fun add(freela: Freelas) {

        freelasList.add(freela)
    }


    fun lookAll(): List<Freelas> {
        return freelasList.sortedBy { it.date } // Enviar uma copia para nao altera a list original e Ordena por list
    }
}