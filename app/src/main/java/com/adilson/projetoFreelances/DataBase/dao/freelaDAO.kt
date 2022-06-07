package com.adilson.projetoFreelances.DataBase.dao

import androidx.room.*
import com.adilson.projetoFreelances.model.Freelas

@Dao
interface freelaDAO {

    @Query("SELECT * FROM Freelas ORDER BY date ASC")
    fun buscaTodos() : List<Freelas>

    //Dont need user upload because now the room check if not exist the element in the DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(vararg freelas: Freelas)

    @Delete
    fun remove(freelas: Freelas)

    @Query(" SELECT * FROM FREELAS WHERE id = :id")
    fun buscarPorId(id: Long) : Freelas?

}