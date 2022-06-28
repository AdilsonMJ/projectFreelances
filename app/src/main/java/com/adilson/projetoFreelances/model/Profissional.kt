package com.adilson.projetoFreelances.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Profissional(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val nome: String?= null,
    val telefone: String?= null
) {


}