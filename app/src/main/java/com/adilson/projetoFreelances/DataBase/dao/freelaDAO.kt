package com.adilson.projetoFreelances.DataBase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.adilson.projetoFreelances.model.Freelas

@Dao
interface freelaDAO {

    @Query("SELECT * FROM Freelas ORDER BY date ASC")
    fun buscaTodos() : List<Freelas>

    @Insert
    fun salva(vararg freelas: Freelas)

    @Delete
    fun remove(freelas: Freelas)

}