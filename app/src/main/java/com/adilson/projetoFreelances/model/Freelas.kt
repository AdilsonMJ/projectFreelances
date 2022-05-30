package com.adilson.projetoFreelances.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class Freelas(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val date: String? = null,
    val horas: String? = null,
    val nomeFotografo: String = "",
    val celular: String = "",
    val noivos: String = "",
    val local: String = ""
) : Parcelable {


    val hasIdValid: Boolean
        get() {
            return id > 0
        }

}