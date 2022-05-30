package com.adilson.projetoFreelances.DataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adilson.projetoFreelances.model.Freelas

@Dao
interface freelaDAO {

    @Query("SELECT * FROM Freelas")
    fun buscaTodos() : List<Freelas>

    @Insert
    fun salva(vararg freelas: Freelas)

}