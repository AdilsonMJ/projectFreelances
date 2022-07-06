package com.adilson.projetoFreelances.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.sql.Date

@Entity
@Parcelize
class Freelas(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var date: Long? = null,
    var horas: String? = null,
    var nomeFotografo: String = "",
    var celular: String = "",
    var noivos: String = "",
    var local: String = ""
) : Parcelable {


    val hasIdValid: Boolean
        get() {
            return id > 0
        }

}