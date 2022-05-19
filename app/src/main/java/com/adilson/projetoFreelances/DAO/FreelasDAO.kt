package com.adilson.projetoFreelances.DAO

import com.adilson.projetoFreelances.model.Freelas

class FreelasDAO {



    companion object {

        private var countID = 1
        private val freelasList = mutableListOf<Freelas>()

    }

    fun add(freela: Freelas){
            freela.setID(countId = countID)
            freelasList.add(freela)
             upDateIdToTheNextUser()
    }

    private fun upDateIdToTheNextUser() {
        countID++
    }

    fun lookAll() : List<Freelas>{
        return freelasList.sortedBy { it.date } // Enviar uma copia para nao altera a list original e Ordena por list
    }


    private fun lookingIfFreelaExist(freela: Freelas): Freelas? {
        for (f in freelasList){
            if (f.id == freela.id){
                return f
            }
        }

        return null
    }


    fun remove (freelaSelect: Freelas){
        val freelaReturn = lookingIfFreelaExist(freelaSelect)
        if (freelaReturn != null){
            freelasList.remove(freelaReturn)
        }
    }

}