package com.adilson.projetoFreelances.model

class Freelas{

    var date: String? = null
    var horas: String? = null
    var nomeFotografo: String? = null
    var celular: String? = null
    var noivos: String? = null
    var local: String? = null

    var id = 0
        private set

      fun setID(countId: Int){
        id = countId
    }

    val hasIdValid: Boolean
        get() {
            return id > 0
        }

}