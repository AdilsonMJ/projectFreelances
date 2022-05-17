package com.adilson.projetoFreelances.DAO

import com.adilson.projetoFreelances.model.Freelas

class FreelasDAO {

    fun add(freela: Freelas){
            freelas.add(freela)
    }

    fun lookAll() : List<Freelas>{
        return freelas.toList() // Enviar uma copia para nao altera a list original
    }

    companion object {
        private val freelas = mutableListOf<Freelas>(
            Freelas("21/11/22", "20:00", "Nilton Reis", "93293232", "Adilson and Lu",  "Igreja de Lourdes")
        )
    }


}